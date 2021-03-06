package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@TaleLin
 * @since 2020-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("spu_detail_img")
public class SpuDetailImgDO extends BaseModel {


    private String img;

    @TableField("`index`")
    private Long index;

    private Long spuId;


}
