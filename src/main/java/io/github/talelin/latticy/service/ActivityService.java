package io.github.talelin.latticy.service;

import io.github.talelin.latticy.dto.ActivityDTO;
import io.github.talelin.latticy.model.ActivityDO;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.model.ActivityDetailDO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-05-27
 */
public interface ActivityService extends IService<ActivityDO> {
    void create(ActivityDTO dto);

    void update(ActivityDTO dto, Integer id);

    void delete(Integer id);

    ActivityDetailDO getDetailById(Integer id);


}
