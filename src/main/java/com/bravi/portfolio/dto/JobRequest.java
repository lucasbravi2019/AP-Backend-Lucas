package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequest {

    private Long id;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String jobRole;

    @NotEmpty
    private String jobTitle;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @Builder.Default
    private Boolean isPresent = Boolean.FALSE;

    @NotNull
    private Long personaId;

}
