package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.model.ThemeDO;
import io.github.talelin.latticy.service.ThemeService;
import io.github.talelin.latticy.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/theme")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/page")
    @LoginRequired
    public PageResponseVO<ThemeDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page
    ) {
        Page<ThemeDO> pager = new Page<>(page, count);
        IPage<ThemeDO> paging = themeService.getBaseMapper().selectPage(pager, null);
        return PageUtil.build(paging);
    }

    @GetMapping("/{id}")
    @LoginRequired
    public ThemeDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        ThemeDO theme = themeService.getBaseMapper().selectById(id);
        if (theme == null) {
            throw new NotFoundException(30000);
        }
        return theme;
    }
}
