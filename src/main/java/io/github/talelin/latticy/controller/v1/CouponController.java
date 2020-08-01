package io.github.talelin.latticy.controller.v1;

import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import io.github.talelin.latticy.dto.CouponDTO;
import io.github.talelin.latticy.service.CouponService;
import io.github.talelin.latticy.vo.CreatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/coupon")
@Validated
@PermissionModule("优惠券")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("")
    @PermissionMeta("创建优惠券")
    @GroupRequired
    public CreatedVO create(@Validated @RequestBody CouponDTO couponDTO) {
        this.couponService.create(couponDTO);
        return new CreatedVO();

    }
}
