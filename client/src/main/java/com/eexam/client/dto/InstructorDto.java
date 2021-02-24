package com.eexam.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<ExamDto> examDtoList;

}