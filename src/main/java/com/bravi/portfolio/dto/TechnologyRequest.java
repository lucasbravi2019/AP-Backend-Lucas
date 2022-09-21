package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechnologyRequest {

    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer level;

    @NotNull
    private Long personaId;

}
