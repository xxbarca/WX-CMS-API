package io.github.talelin.latticy.controller.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.common.util.PageUtil;
import io.github.talelin.latticy.mapper.OrderMapper;
import io.github.talelin.latticy.model.OrderDO;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.service.OrderService;
import io.github.talelin.latticy.service.impl.OrderServiceImpl;
import io.github.talelin.latticy.vo.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(value = "/page")
    public PageResponseVO<OrderDO> getOrderList(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page
    ) {
        Page<OrderDO> pager = new Page<>(page, count);
        IPage<OrderDO> paging = orderMapper.selectPage(pager, null);
        return PageUtil.build(paging);
    }
}
