package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.dto.OrderDTO;
import io.github.talelin.latticy.mapper.OrderMapper;
import io.github.talelin.latticy.model.OrderDO;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.service.OrderService;
import io.github.talelin.latticy.service.impl.OrderServiceImpl;
import io.github.talelin.latticy.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import qiniu.happydns.util.IP;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping(value = "/page")
    public PageResponseVO<OrderDO> getOrderList(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page,
            @RequestBody OrderDTO orderDTO
            ) {
        System.out.println(orderDTO.getOrder_no());
        Page<OrderDO> pager = new Page<>(page, count);
        QueryWrapper<OrderDO> queryWrapper = new QueryWrapper<>();
        if (!orderDTO.getOrder_no().isEmpty()) {
            queryWrapper.like("order_no", orderDTO.getOrder_no());
        }
        if (!orderDTO.getSnap_title().isEmpty()) {
            queryWrapper.like("snap_title", orderDTO.getSnap_title());
        }
        if (!orderDTO.getStatus().isEmpty()) {
            queryWrapper.eq("status", orderDTO.getSnap_title());
        }
        IPage<OrderDO> paging = orderMapper.selectPage(pager, queryWrapper);
        return PageUtil.build(paging);
    }
}
