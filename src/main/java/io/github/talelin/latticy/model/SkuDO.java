package io.github.talelin.latticy.model;

import java.math.BigDecimal;
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
@TableName("sku")
public class SkuDO extends BaseModel {


    private Long categoryId;

    private String code;

    private BigDecimal discountPrice;

    private String img;

    private Integer online;

    private BigDecimal price;

    private Long rootCategoryId;

    private String specs;

    private Long spuId;

    private Long stock;

    private String title;


}
