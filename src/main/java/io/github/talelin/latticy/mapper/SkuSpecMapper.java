package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.model.SkuSpecDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkuSpecMapper extends BaseMapper<SkuSpecDO> {
    /**
     * 根据skuId和spuId删除sku_spec记录
     * @param spuId Integer
     * @param skuId Integer
     */
    void deleteSpecs(Long spuId, Long skuId);

    /**
     * 获取已选的specId
     * @param keyId   规格键 id
     * @param skuId   sku id
     * @return 规格值id
     */
    Integer getSpecValueId(Integer keyId, Integer skuId);

    /**
     * 根据规格值id获取sku的id列表
     * @param valueId 规格值id
     * @return sku的id列表
     */
    List<Integer> getSkuIdsByValueId(Integer valueId);
}
