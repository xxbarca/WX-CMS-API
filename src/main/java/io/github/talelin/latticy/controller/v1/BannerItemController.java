package io.github.talelin.latticy.controller.v1;


import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.latticy.dto.BannerDTO;
import io.github.talelin.latticy.dto.BannerItemDTO;
import io.github.talelin.latticy.service.BannerItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.github.talelin.latticy.model.BannerItemDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

/**
* @author generator@TaleLin
* @since 2020-05-28
*/
@RestController
@RequestMapping("/v1/banner-item")
public class BannerItemController {

    // TODO - 3-1商品管理操作需求分析

    @Autowired
    private BannerItemService bannerItemService;

    @PostMapping
    @PermissionMeta(value = "创建Banner Item")
    public CreatedVO create(@Validated @RequestBody BannerItemDTO bannerItemDTO) {
        BannerItemDO bannerItemDO = new BannerItemDO();
        BeanUtils.copyProperties(bannerItemDTO, bannerItemDO);
        this.bannerItemService.create(bannerItemDO);
        return new CreatedVO();
    }

    @PutMapping("/{id}")
    @PermissionMeta(value = "更新Banner item")
    public UpdatedVO update(
            @Validated @RequestBody BannerItemDTO bannerItemDTO,
            @PathVariable @Positive(message = "{id.positive}") Integer id) {
        this.bannerItemService.update(bannerItemDTO, id);
        return new UpdatedVO();
    }

    @DeleteMapping("/{id}")
    @PermissionMeta(value = "删除Banner item")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        this.bannerItemService.delete(id);
        return new DeletedVO();
    }

    @GetMapping("/{id}")
    public BannerItemDO get(@PathVariable(value = "id") @Positive(message = "{id.positive}") Long id) {
        return null;
    }

    @GetMapping("/page")
    public PageResponseVO<BannerItemDO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Long count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Long page
    ) {
        return null;
    }

}
