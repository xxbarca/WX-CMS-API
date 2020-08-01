package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.common.enumeration.CategoryRootOrNotEnum;
import io.github.talelin.latticy.dto.CategoryDTO;
import io.github.talelin.latticy.model.CategoryDO;
import io.github.talelin.latticy.mapper.CategoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-29
 */
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, CategoryDO> {

    public CategoryDO getCategoryById(Long id) {
        CategoryDO categoryDO = this.getById(id);
        if (categoryDO == null) {
            throw new NotFoundException(40000);
        }
        return categoryDO;
    }

    public void updateCategory(CategoryDTO dto, Integer id) {
        CategoryDO categoryDO = this.getBaseMapper().selectById(id);
        if (categoryDO == null) {
            throw new NotFoundException(40000);
        }
        BeanUtils.copyProperties(dto, categoryDO);
        this.getBaseMapper().updateById(categoryDO);
    }

    public void deleteCategory(Integer id) {
        CategoryDO category = this.getBaseMapper().selectById(id);
        if (category == null) {
            throw new NotFoundException(40000);
        }
        if (category.getIsRoot() == CategoryRootOrNotEnum.ROOT.getValue()) {
            // 查找当前父分类下有无子分类，如有则不能删除
            QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(CategoryDO::getParentId, id);
            wrapper.lambda().eq(CategoryDO::getIsRoot, CategoryRootOrNotEnum.NOT_ROOT.getValue());
            CategoryDO categoryDO = this.getBaseMapper().selectOne(wrapper);
            if (categoryDO != null) {
                throw new ForbiddenException(40001);
            }
        }
        this.getBaseMapper().deleteById(id);
    }

}
