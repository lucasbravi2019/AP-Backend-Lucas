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
public class PersonaResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private AboutResponse about;

    @Builder.Default
    private List<TechnologyResponse> technologyList = new ArrayList<>();

    @Builder.Default
    private List<ContactResponse> contactList = new ArrayList<>();

    @Builder.Default
    private List<ProjectResponse> projectList = new ArrayList<>();

    @Builder.Default
    private List<JobResponse> jobList = new ArrayList<>();

    @Builder.Default
    private List<EducationResponse> educationList = new ArrayList<>();

}
