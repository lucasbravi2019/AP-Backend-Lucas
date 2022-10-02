package com.bravi.portfolio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String projectDescription;
    private String image;
    private String site;

    @ManyToOne
    private Persona persona;

    @Builder.Default
    @ManyToMany
    private Set<Technology> technologyList = new HashSet<>();
}
