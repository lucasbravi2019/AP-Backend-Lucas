package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {

    @NotNull
    private Long id;

    @NotEmpty
    private String projectName;

    @NotEmpty
    private String projectDescription;

    @Builder.Default
    private List<Long> technologyList = new ArrayList<>();

    @NotNull
    private Long personaId;
}
