package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.BannerItemDTO;
import io.github.talelin.latticy.model.BannerItemDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-28
 */
public interface BannerItemService extends IService<BannerItemDO> {

    void create(BannerItemDO bannerItemDO);

    void update(BannerItemDTO bannerItemDTO, Integer id);

}
