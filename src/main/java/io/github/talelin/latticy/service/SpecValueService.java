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

    /**
     * 创建规格值
     * @param dto
     * */
    void create(SpecValueDTO dto);

    /**
     * 删除规格值
     * @param id
     * */
    void delete(Integer id);
}
