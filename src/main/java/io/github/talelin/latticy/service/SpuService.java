package io.github.talelin.latticy.service;

import io.github.talelin.latticy.model.SpuDO;
import io.github.talelin.latticy.mapper.SpuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.model.SpuDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-28
 */
@Service
public class SpuService extends ServiceImpl<SpuMapper, SpuDO> {

    public SpuDetailDO getDetail(Long id) {
        return this.getBaseMapper().getDetail(id);
    }

}
