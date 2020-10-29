package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.mapper.ThemeMapper;
import io.github.talelin.latticy.mapper.ThemeSpuMapper;
import io.github.talelin.latticy.model.SimplifySpuDO;
import io.github.talelin.latticy.model.SpuDO;
import io.github.talelin.latticy.model.ThemeDO;
import io.github.talelin.latticy.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, ThemeDO> implements ThemeService {

    @Autowired
    private ThemeSpuMapper themeSpuMapper;
    @Override
    public List<SimplifySpuDO> getSpus(Integer id) {
        return this.getBaseMapper().getSpus(id);
    }

    @Override
    public void deleteThemeSpu(Integer id) {
        themeSpuMapper.deleteById(id);

    }

    @Override
    public List<SpuDO> getSimplifySpus(Integer id) {
        return themeSpuMapper.getSimplifySpus(id);
    }
}
