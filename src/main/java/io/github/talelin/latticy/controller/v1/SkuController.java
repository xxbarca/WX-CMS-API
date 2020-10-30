package io.github.talelin.latticy.controller.v1;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author generator@TaleLin
* @since 2020-05-28
*/
@RestController
@RequestMapping("/v1/sku")
@Validated
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/by/spu/{id}")
    public List<SkuDO> getBySpuId(@PathVariable(value = "id") @Positive Long spuId) {
        return this.skuService.getBaseMapper().selectList(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getSpuId, spuId));
//        return this.skuService.lambdaQuery().eq(SkuDO::getSpuId, spuId).list();
    }

    /**
     * 获取sku列表
     * */
    @GetMapping("/page")
    @LoginRequired
    public PageResponseVO<SkuDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page
    ) {
        Page<SkuDO> pager = new Page<>(page, count);
        IPage<SkuDO> paging = skuService.getBaseMapper().selectPage(pager, null);
        return PageUtil.build(paging);
    }

}
