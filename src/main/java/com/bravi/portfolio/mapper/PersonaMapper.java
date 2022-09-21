package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.*;
import com.bravi.portfolio.entity.*;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PersonaMapper {

    private final PersonaRepository personaRepository;

    public Persona toEntity(PersonaRequest personaRequest) {
        Persona persona = Persona.builder().build();
        if (personaRequest.getId() != null) {
            persona = personaRepository.findById(personaRequest.getId())
                    .orElseThrow();
        }
        persona.setFirstName(personaRequest.getFirstName());
        persona.setLastName(personaRequest.getLastName());

        return persona;
    }

    public PersonaResponse toDto(Persona persona) {
        PersonaResponse personaResponse = PersonaResponse.builder()
                .id(persona.getId())
                .firstName(persona.getFirstName())
                .lastName(persona.getLastName())
                .build();

        if (persona.getAbout() != null) {
            About about = persona.getAbout();
            AboutResponse aboutResponse = AboutResponse.builder()
                    .id(about.getId())
                    .aboutMsg(about.getAboutMsg())
                    .build();

            personaResponse.setAbout(aboutResponse);
        }

        if (!CollectionUtils.isEmpty(persona.getContactList())) {
            List<Contact> contactList = persona.getContactList();
            List<ContactResponse> contactResponseList = contactList.stream()
                    .map(contact -> ContactResponse.builder()
                            .id(contact.getId())
                            .contactValue(contact.getContactValue())
                            .contactType(contact.getContactType())
                            .build())
                    .collect(Collectors.toList());

            personaResponse.setContactList(contactResponseList);
        }

        if (!CollectionUtils.isEmpty(persona.getEducationList())) {
            List<Education> educationList = persona.getEducationList();
            List<EducationResponse> educationResponseList = educationList.stream().map(education -> EducationResponse.builder()
                            .id(education.getId())
                            .title(education.getTitle())
                            .institute(education.getInstitute())
                            .startDate(education.getStartDate())
                            .endDate(education.getEndDate())
                            .build())
                    .collect(Collectors.toList());

            personaResponse.setEducationList(educationResponseList);
        }

        if (!CollectionUtils.isEmpty(persona.getJobList())) {
            List<Job> jobList = persona.getJobList();
            List<JobResponse> jobResponseList = jobList.stream().map(job -> JobResponse.builder()
                            .id(job.getId())
                            .jobTitle(job.getJobTitle())
                            .jobRole(job.getJobRole())
                            .companyName(job.getCompanyName())
                            .startDate(job.getStartDate())
                            .endDate(job.getEndDate())
                            .build())
                    .collect(Collectors.toList());

            personaResponse.setJobList(jobResponseList);
        }

        if (!CollectionUtils.isEmpty(persona.getProjectList())) {
            List<Project> projectList = persona.getProjectList();
            List<ProjectResponse> projectResponseList = projectList.stream()
                    .map(project -> {
                        ProjectResponse response = ProjectResponse.builder()
                                .id(project.getId())
                                .projectName(project.getProjectName())
                                .projectDescription(project.getProjectDescription())
                                .build();

                        if (!CollectionUtils.isEmpty(project.getTechnologyList())) {
                            List<Technology> technologyList = project.getTechnologyList();
                            List<TechnologyResponse> technologyResponseList = technologyList.stream()
                                    .map(technology -> TechnologyResponse.builder()
                                            .id(technology.getId())
                                            .level(technology.getLevel())
                                            .name(technology.getName())
                                            .build())
                                    .collect(Collectors.toList());

                            response.setTechnologyList(technologyResponseList);
                        }

                        return response;
                    })
                    .collect(Collectors.toList());

            personaResponse.setProjectList(projectResponseList);
        }

        if (!CollectionUtils.isEmpty(persona.getTechnologyList())) {
            List<Technology> technologyList = persona.getTechnologyList();
            List<TechnologyResponse> technologyResponseList = technologyList.stream()
                    .map(technology -> TechnologyResponse.builder()
                            .id(technology.getId())
                            .name(technology.getName())
                            .level(technology.getLevel())
                            .build())
                    .collect(Collectors.toList());

            personaResponse.setTechnologyList(technologyResponseList);
        }

        return personaResponse;
    }



}
