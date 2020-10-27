package io.github.talelin.latticy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultInfo {
    private Integer code;
    private String msg;
    private String info;
}
