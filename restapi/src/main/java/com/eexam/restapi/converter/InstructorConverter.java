package com.eexam.restapi.converter;

import com.eexam.restapi.entity.Instructor;
import com.eexam.restapi.payload.ExamDto;
import com.eexam.restapi.payload.InstructorDto;

import java.util.List;

public class InstructorConverter {

    public static InstructorDto getInstuctorDto(Instructor instructor){

        List<ExamDto> examDtos = ExamConverter.getExamDtos(instructor.getCreatedExams());

        InstructorDto instructorDto = InstructorDto.builder()
                .email(instructor.getEmail())
                .examDtoList(examDtos)
                .firstName(instructor.getFirstName())
                .lastName(instructor.getLastName())
                .username(instructor.getUsername())
                .id(instructor.getId())
                .build();

        return instructorDto;
    }
}
