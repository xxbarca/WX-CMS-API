package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.mapper.SkuMapper;
import io.github.talelin.latticy.mapper.SkuSpecMapper;
import io.github.talelin.latticy.model.SkuDO;
import io.github.talelin.latticy.model.SkuSpecDO;
import io.github.talelin.latticy.service.SkuSpecService;
import org.springframework.stereotype.Service;

@Service
public class SkuSpecServiceImpl extends ServiceImpl<SkuSpecMapper, SkuSpecDO> implements SkuSpecService {

}
