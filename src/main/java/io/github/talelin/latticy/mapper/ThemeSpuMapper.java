package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.SpuDO;
import io.github.talelin.latticy.model.ThemeSpuDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeSpuMapper extends BaseMapper<ThemeSpuDO> {

    List<SpuDO> getSimplifySpus(Integer id);
}
