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
public class ProjectResponse {

    private Long id;
    private String projectName;
    private String projectDescription;
    private byte[] image;

    @Builder.Default
    private List<TechnologyResponse> technologyList = new ArrayList<>();
}
