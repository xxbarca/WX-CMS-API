package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.SpecValueDTO;
import io.github.talelin.latticy.model.SpecValueDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-29
 */
public interface SpecValueService extends IService<SpecValueDO> {
    void create(SpecValueDTO dto);
}
