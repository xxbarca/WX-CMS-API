package io.github.talelin.latticy.service.impl;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.dto.ActivityDTO;
import io.github.talelin.latticy.mapper.CouponMapper;
import io.github.talelin.latticy.model.ActivityDO;
import io.github.talelin.latticy.mapper.ActivityMapper;
import io.github.talelin.latticy.model.ActivityDetailDO;
import io.github.talelin.latticy.service.ActivityService;
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
 * @since 2020-05-27
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, ActivityDO> implements ActivityService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public void create(ActivityDTO dto) {
        ActivityDO activityDO = new ActivityDO();
        BeanUtils.copyProperties(dto, activityDO);
        this.getBaseMapper().insert(activityDO);
    }

    @Override
    public void update(ActivityDTO dto, Integer id) {
        ActivityDO activityDO = this.getBaseMapper().selectById(id);
        if (activityDO == null) {
            throw new NotFoundException(90000);
        }
        BeanUtils.copyProperties(dto, activityDO);
        this.getBaseMapper().updateById(activityDO);
    }

    @Override
    public void delete(Integer id) {
        ActivityDO activityDO = this.getBaseMapper().selectById(id);
        if (activityDO == null) {
            throw new NotFoundException(90000);
        }
        this.getBaseMapper().deleteById(id);

    }

    @Override
    public ActivityDetailDO getDetailById(Integer id) {
        ActivityDO activityDO = this.getBaseMapper().selectById(id);
        if (activityDO == null) {
            throw new NotFoundException(90000);
        }

        List<Integer> coupons = couponMapper.getCouponsByActivityId(id);
        ActivityDetailDO activityDetailDO = new ActivityDetailDO();
        activityDetailDO.setCouponIds(coupons);
        BeanUtils.copyProperties(activityDO, activityDetailDO);

        return activityDetailDO;
    }
}
