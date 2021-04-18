package io.github.talelin.latticy.dto;

import lombok.Data;

@Data
public class OrderDTO {
    // 订单编号
    private String order_no;

    // 标题名
    private String snap_title;

    // 状态
    private String status;
}
