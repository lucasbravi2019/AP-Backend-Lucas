package com.bravi.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponse {

    private Long id;
    private String institute;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

}
