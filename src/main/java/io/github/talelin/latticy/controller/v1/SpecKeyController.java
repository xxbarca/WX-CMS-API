package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.service.SpecKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import io.github.talelin.latticy.model.SpecKeyDO;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.PageResponseVO;
import io.github.talelin.latticy.vo.UpdatedVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author generator@TaleLin
* @since 2020-05-29
*/
@RestController
@RequestMapping("/v1/spec-key")
public class SpecKeyController {

    @Autowired
    private SpecKeyService specKeyService;

    @RequestMapping("/by/spu/{id}")
    public List<SpecKeyDO> getBySpuId(@PathVariable(value = "id") @Positive Long id) {
        return this.specKeyService.getBySpuId(id);
    }


}
