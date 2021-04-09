package io.github.talelin.latticy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.SpecValueDTO;
import io.github.talelin.latticy.mapper.SkuMapper;
import io.github.talelin.latticy.mapper.SkuSpecMapper;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.model.SkuSpecDO;
import io.github.talelin.latticy.model.SpecKeyValueDO;
import io.github.talelin.latticy.model.SpecValueDO;
import io.github.talelin.latticy.mapper.SpecValueMapper;
import io.github.talelin.latticy.service.SpecValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-29
 */
@Service
public class SpecValueServiceImpl extends ServiceImpl<SpecValueMapper, SpecValueDO> implements SpecValueService {

    @Autowired
    private SkuSpecMapper skuSpecMapper;

    @Autowired
    private SpecValueMapper specValueMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void create(SpecValueDTO dto) {
        SpecValueDO specValueDO = new SpecValueDO();
        BeanUtils.copyProperties(dto, specValueDO);
        this.save(specValueDO);
    }

    @Override
    public void delete(Integer id) {
        SpecValueDO specValue = this.getById(id);
        if (specValue == null) {
            throw new NotFoundException(60002);
        }
        this.getBaseMapper().deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SpecValueDTO dto, Integer id) {

        SpecValueDO specValue = this.getById(id);
        if (specValue == null) {
            throw new NotFoundException(60002);
        }
        BeanUtils.copyProperties(dto, specValue);
        this.updateById(specValue);

        List<Integer> skuIds = skuSpecMapper.getSkuIdsByValueId(id);
        if (skuIds.isEmpty()) {
            return;
        }

        List<SkuDO> skuList = skuMapper.selectBatchIds(skuIds);
        skuList.forEach(sku -> {

            JSONArray tableData = JSONArray.parseArray(sku.getSpecs());
            List<SpecKeyValueDO> specKeyValueDOList = tableData.toJavaList(SpecKeyValueDO.class);
            specKeyValueDOList.forEach(item -> {
                if (item.getValueId() == id) {
                    SpecValueDO specValueDO = specValueMapper.selectById(id);
//                    System.out.println("value = " + specValueDO.getValue());
                    item.setValue(specValueDO.getValue());
                }
            });
            String string = JSON.toJSONString(specKeyValueDOList);
            sku.setSpecs(string);
            skuMapper.updateById(sku);
        });
    }
}
