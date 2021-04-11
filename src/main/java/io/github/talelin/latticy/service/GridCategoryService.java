package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.dto.GridCategoryDTO;
import io.github.talelin.latticy.model.GridCategoryDO;

public interface GridCategoryService extends IService<GridCategoryDO> {
    void createGridCategory(GridCategoryDTO dto);
    void deleteGridCategory(Integer id);
}
