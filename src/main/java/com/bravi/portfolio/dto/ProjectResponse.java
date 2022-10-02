package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {

    private Long id;
    private String projectName;
    private String projectDescription;
    private String site;

    @Builder.Default
    private Set<TechnologyResponse> technologyList = new HashSet<>();
    private byte[] image;
}
