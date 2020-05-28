package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.bo.BannerWithItemsBO;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.dto.BannerDTO;
import io.github.talelin.latticy.model.BannerDO;
import io.github.talelin.latticy.service.BannerService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RequestMapping("/v1/banner")
@RestController
@Validated
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
     * 添加Banner
     * */
    @PostMapping
    public CreatedVO create(@RequestBody @Validated BannerDTO bannerDTO) {
        BannerDO bannerDO = new BannerDO();
        BeanUtils.copyProperties(bannerDTO, bannerDO);
        this.bannerService.create(bannerDO);
        return new CreatedVO();
    }

    /**
     * 查询 `banner` 关联 `banner_item`
     * @param id: banner的id
     * */
    @GetMapping("/{id}")
    public BannerWithItemsBO getWithItems(@PathVariable @Positive Long id) {
        return bannerService.getWithItems(id);
    }

    /***
     * Banner
     * @param page:
     * @param count:
     * */
    @GetMapping("/page")
    public PageResponseVO<BannerDO> getBanners(@RequestParam(required = false, defaultValue = "0") @Min(value = 0) Integer page,
                                               @RequestParam(required = false, defaultValue = "10") @Min(value = 1) @Max(value = 30) Integer count) {
        Page<BannerDO> pager = new Page<>(page, count);
        IPage<BannerDO> paging = bannerService.getBaseMapper().selectPage(pager, null);
        return new PageResponseVO<>(paging.getTotal(), paging.getRecords(), paging.getCurrent(), paging.getSize());
    }

    /**
     * 更新
     * @param bannerDTO:
     * @param id:
     * */
    @PutMapping("/{id}")
    public UpdatedVO update(@RequestBody @Validated BannerDTO bannerDTO,
                            @PathVariable @Positive Long id) {
        bannerService.update(bannerDTO, id);
        return new UpdatedVO<>();
    }

    /**
     * 删除
     * @param id:
     * */
    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive Long id) {
        bannerService.delete(id);
        return new DeletedVO();
    }

}
