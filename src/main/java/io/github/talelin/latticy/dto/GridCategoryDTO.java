package io.github.talelin.latticy.dto;

import io.github.talelin.autoconfigure.validator.Length;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class GridCategoryDTO {

    @Length(min = 1, max = 100)
    private String title;

    @Length(min = 1, max = 100)
    private String name;

    @NotBlank
    @Length(min = 1, max = 255)
    private String img;

    @NotNull
    @Positive
    private Integer categoryId;
}
