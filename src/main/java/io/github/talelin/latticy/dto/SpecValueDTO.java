package io.github.talelin.latticy.dto;

import io.github.talelin.autoconfigure.validator.Length;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SpecValueDTO {

    @NotBlank
    @Length(min = 1, max = 255)
    private String value;

    @Length(min = 1, max = 255)
    private String extend;

    @Positive
    @NotNull
    private Integer specId;
}
