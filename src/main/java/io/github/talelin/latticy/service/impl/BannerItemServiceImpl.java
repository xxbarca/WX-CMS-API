package io.github.talelin.latticy.service.impl;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.BannerItemDTO;
import io.github.talelin.latticy.model.BannerItemDO;
import io.github.talelin.latticy.mapper.BannerItemMapper;
import io.github.talelin.latticy.service.BannerItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-28
 */
@Service
public class BannerItemServiceImpl extends ServiceImpl<BannerItemMapper, BannerItemDO> implements BannerItemService {

    @Autowired
    private BannerItemMapper bannerItemMapper;

    @Override
    public void create(BannerItemDO bannerItemDO) {
        this.getBaseMapper().insert(bannerItemDO);
    }

    @Override
    public void update(BannerItemDTO bannerItemDTO, Integer id) {
        BannerItemDO bannerItemDO = this.getBaseMapper().selectById(id);
        if (bannerItemDO == null) {
            throw new NotFoundException(20001);
        }
        BeanUtils.copyProperties(bannerItemDTO, bannerItemDO);
        this.getBaseMapper().updateById(bannerItemDO);
    }

    @Override
    public void delete(Integer id) {
        BannerItemDO bannerItem = this.getById(id);
        if (bannerItem == null) {
            throw new NotFoundException(20001);
        }
        this.getBaseMapper().deleteById(id);

    }
}
