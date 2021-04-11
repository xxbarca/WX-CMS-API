package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.mapper.GridCategoryMapper;
import io.github.talelin.latticy.model.GridCategoryDO;
import io.github.talelin.latticy.service.GridCategoryService;
import org.springframework.stereotype.Service;

@Service
public class GridCategoryServiceImpl extends ServiceImpl<GridCategoryMapper, GridCategoryDO> implements GridCategoryService {
}
