package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.SkuDTO;
import io.github.talelin.latticy.mapper.SkuSpecMapper;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.mapper.SkuMapper;
import io.github.talelin.latticy.model.SpuDO;
import io.github.talelin.latticy.service.SkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.service.SkuSpecService;
import io.github.talelin.latticy.service.SpuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SkuServiceImpl extends ServiceImpl<SkuMapper, SkuDO> implements SkuService {


    @Autowired
    private SkuSpecServiceImpl skuSpecService;

    @Autowired
    private SkuSpecMapper skuSpecMapper;

    @Autowired
    private SpuService spuService;

    @Override
    public void delete(Integer id) {
        // 删除SKU
        SkuDO skuDO = this.getBaseMapper().selectById(id);
        if (skuDO == null) {
            throw new NotFoundException(80002);
        }
        // 删除sku
        this.getBaseMapper().deleteById(id);

//        SkuSpecService
        skuSpecService.getBaseMapper().deleteSpecs(skuDO.getSpuId(), skuDO.getId());
    }

    @Override
    public void create(SkuDTO dto) {
        // 检测
        QueryWrapper<SkuDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SkuDO::getTitle, dto.getTitle());
        int count = this.count(queryWrapper);
        if (count > 0) {
            throw new ForbiddenException(80000);
        }
        SpuDO spuDO = spuService.getById(dto.getSpuId());
        if (spuDO == null) {
            throw new NotFoundException(70000);
        }

        // 设置存储的sku
        SkuDO sku = new SkuDO();
        BeanUtils.copyProperties(dto, sku);
//        String code = this.generateSkuCode(selectors, dto.getSpuId());
        sku.setCategoryId(spuDO.getCategoryId());
        sku.setRootCategoryId(spuDO.getRootCategoryId());
        this.save(sku);
    }
}
