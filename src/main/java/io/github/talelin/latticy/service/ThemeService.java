package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.SimplifySpuDO;
import io.github.talelin.latticy.model.ThemeDO;

import java.util.List;

public interface ThemeService extends IService<ThemeDO> {

    List<SimplifySpuDO> getSpus(Integer id);

    void deleteThemeSpu(Integer id);
}
