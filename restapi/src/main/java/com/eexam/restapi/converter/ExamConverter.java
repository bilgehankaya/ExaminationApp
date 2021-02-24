package com.eexam.restapi.converter;

import com.eexam.restapi.entity.Exam;
import com.eexam.restapi.payload.ExamDto;
import com.eexam.restapi.payload.ResultDto;

import java.util.ArrayList;
import java.util.List;

public class ExamConverter {

    public static ExamDto getExamDto(Exam exam){

        List<ResultDto> resultDtoList = ResultConverter.getResultDtoList(exam.getResults());

        ExamDto examDto = ExamDto.builder()
                .id(exam.getId())
                .creatorId(exam.getCreator().getId())
                .results(resultDtoList)
                .finishDate(exam.getFinishDate())
                .startDate(exam.getStartDate())
                .title(exam.getTitle())
                .questions(exam.getQuestions())
                .uniqueURL(exam.getUniqueURL())
                .build();

        return examDto;
    }


    public static List<ExamDto> getExamDtos(List<Exam> exams){

        List<ExamDto> examDtos = new ArrayList<>();

        for (Exam exam: exams) {
            List<ResultDto> resultDtoList = ResultConverter.getResultDtoList(exam.getResults());

            ExamDto examDto = ExamDto.builder()
                    .id(exam.getId())
                    .results(resultDtoList)
                    .finishDate(exam.getFinishDate())
                    .startDate(exam.getStartDate())
                    .title(exam.getTitle())
                    .questions(exam.getQuestions())
                    .uniqueURL(exam.getUniqueURL())
                    .build();

            examDtos.add(examDto);
        }

        return examDtos;

    }
}
