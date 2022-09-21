package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContactRequest {

    @NotNull
    private Long id;

    @NotEmpty
    private String contactType;

    @NotEmpty
    private String contactValue;

    @NotNull
    private Long personaId;

}
