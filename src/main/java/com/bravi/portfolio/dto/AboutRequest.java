package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AboutRequest {

    @NotNull
    private Long id;

    @NotEmpty
    private String aboutMsg;

    @NotNull
    private Long personaId;

}
