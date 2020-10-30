package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.autoconfigure.exception.ForbiddenException;
import io.github.talelin.latticy.dto.SpecKeyDTO;
import io.github.talelin.latticy.model.SpecKeyDO;
import io.github.talelin.latticy.mapper.SpecKeyMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SpecKeyService extends ServiceImpl<SpecKeyMapper, SpecKeyDO> {


    public List<SpecKeyDO> getBySpuId(Long spuId) {

        return this.baseMapper.getBySpuId(spuId);

    }

    public void create(SpecKeyDTO dto) {
        QueryWrapper<SpecKeyDO> wrapper = new QueryWrapper();
        wrapper.lambda().eq(SpecKeyDO::getName, dto.getName());
        SpecKeyDO existed = this.getOne(wrapper);
        if (existed != null) {
            throw new ForbiddenException(60000);
        }

        SpecKeyDO specKey = new SpecKeyDO();
        BeanUtils.copyProperties(dto, specKey);
        this.save(specKey);

    }

}
