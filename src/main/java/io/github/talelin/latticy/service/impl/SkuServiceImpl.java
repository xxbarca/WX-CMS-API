package io.github.talelin.latticy.service.impl;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.mapper.SkuSpecMapper;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.mapper.SkuMapper;
import io.github.talelin.latticy.service.SkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.service.SkuSpecService;
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

    @Override
    public void delete(Integer id) {
        // 删除SKU
        SkuDO skuDO = this.getBaseMapper().selectById(id);
        if (skuDO == null) {
            throw new NotFoundException(80002);
        }
        // 删除sku
        this.getBaseMapper().deleteById(id);

        // sku_spec
//        SkuSpecService
        skuSpecService.getBaseMapper().deleteSpecs(skuDO.getSpuId(), skuDO.getId());
    }
}
