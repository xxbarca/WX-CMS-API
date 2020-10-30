package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.SkuDTO;
import io.github.talelin.latticy.mapper.SkuMapper;
import io.github.talelin.latticy.model.SkuDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-28
 */
public interface SkuService extends IService<SkuDO> {

    void delete(Integer id);

    void create(SkuDTO dto);

    void update(SkuDTO dto, Integer id);

}
