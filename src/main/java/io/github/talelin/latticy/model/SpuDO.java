package io.github.talelin.latticy.model;

import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@TaleLin
 * @since 2020-05-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("spu")
public class SpuDO extends BaseModel {


    private Long categoryId;

    private Long defaultSkuId;

    private String description;

    private String discountPrice;

    private String forThemeImg;

    private String img;

    private Integer isTest;

    private Integer online;

    private String price;

    private Long rootCategoryId;

    private Integer sketchSpecId;

    private String subtitle;

    private String tags;

    private String title;

    private String spuThemeImg;

}
