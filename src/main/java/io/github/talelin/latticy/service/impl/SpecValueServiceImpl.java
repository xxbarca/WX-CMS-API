package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.dto.SpecValueDTO;
import io.github.talelin.latticy.model.SpecValueDO;
import io.github.talelin.latticy.mapper.SpecValueMapper;
import io.github.talelin.latticy.service.SpecValueService;
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
public class SpecValueServiceImpl extends ServiceImpl<SpecValueMapper, SpecValueDO> implements SpecValueService {

    @Override
    public void create(SpecValueDTO dto) {
        SpecValueDO specValueDO = new SpecValueDO();
        BeanUtils.copyProperties(dto, specValueDO);
        this.save(specValueDO);
    }
}
