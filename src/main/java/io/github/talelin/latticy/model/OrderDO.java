package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@TableName("`order`")
public class OrderDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderNo;

    private BigDecimal totalPrice;

    private Integer UserId;

    private Integer totalCount;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date deleteTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expiredTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date placedTime;

    private String snapImg;

    private String snapTitle;

    private String snapAddress;

    private BigDecimal finalTotalPrice;

    private Integer status;


}
