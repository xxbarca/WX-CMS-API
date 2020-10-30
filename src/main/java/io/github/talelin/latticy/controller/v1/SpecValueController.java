package io.github.talelin.latticy.controller.v1;


import io.github.talelin.latticy.service.impl.SpecValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import io.github.talelin.latticy.model.SpecValueDO;
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
@RequestMapping("/v1/spec-value")
public class SpecValueController {

    @Autowired
    private SpecValueServiceImpl specValueService;

    @GetMapping("/by/spec-key/{id}")
    public List<SpecValueDO> getBySpecKeyId(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        return this.specValueService.lambdaQuery().eq(SpecValueDO::getSpecId, id).list();
    }

}
