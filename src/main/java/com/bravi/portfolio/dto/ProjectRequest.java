package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {

    private Long id;

    private String projectName;

    private String projectDescription;

    private String site;

    @Builder.Default
    private List<Long> technologyList = new ArrayList<>();

    private Long personaId;
}
