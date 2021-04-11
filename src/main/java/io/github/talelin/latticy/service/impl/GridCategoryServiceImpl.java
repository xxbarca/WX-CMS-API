package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.GridCategoryDTO;
import io.github.talelin.latticy.mapper.CategoryMapper;
import io.github.talelin.latticy.mapper.GridCategoryMapper;
import io.github.talelin.latticy.model.CategoryDO;
import io.github.talelin.latticy.model.GridCategoryDO;
import io.github.talelin.latticy.service.GridCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GridCategoryServiceImpl extends ServiceImpl<GridCategoryMapper, GridCategoryDO> implements GridCategoryService {

    @Value("${sleeve.grid-category-maximum-quantity}")
    private int maximumQuality;


    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public void createGridCategory(GridCategoryDTO dto) {
        Integer count = this.getBaseMapper().selectCount(null);
        if (count >= maximumQuality) {
            throw new ForbiddenException(50001);
        }
        CategoryDO category = categoryMapper.selectById(dto.getCategoryId());
        if (category == null) {
            throw new NotFoundException(40000);
        }

        GridCategoryDO gridCategory = new GridCategoryDO();
        BeanUtils.copyProperties(dto, gridCategory);
        gridCategory.setRootCategoryId(category.getParentId().intValue());
        this.save(gridCategory);
    }

    @Override
    public void deleteGridCategory(Integer id) {
        GridCategoryDO gridCategory = this.getById(id);
        if (gridCategory == null) {
            throw new NotFoundException(50000);
        }
        this.getBaseMapper().deleteById(id);
    }
}
