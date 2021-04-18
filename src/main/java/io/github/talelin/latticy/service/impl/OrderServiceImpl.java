package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.mapper.LogMapper;
import io.github.talelin.latticy.mapper.OrderMapper;
import io.github.talelin.latticy.model.LogDO;
import io.github.talelin.latticy.model.OrderDO;
import io.github.talelin.latticy.service.LogService;
import io.github.talelin.latticy.service.OrderService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService {
}
