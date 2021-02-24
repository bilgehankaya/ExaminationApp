package com.eexam.restapi.converter;

import com.eexam.restapi.entity.Result;
import com.eexam.restapi.payload.ResultDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResultConverter {

    public static List<ResultDto> getResultDtoList(List<Result> resultList){

        List<ResultDto> resultDtos = resultList.stream()
                .map(r -> ResultDto.builder()
                        .joined(r.isJoined())
                        .resultId(r.getId())
                        .examId(r.getExam().getId())
                        .examTitle(r.getExam().getTitle())
                        .incorrectNumber(r.getIncorrectNumber())
                        .participantId(r.getParticipant().getId())
                        .participantFullName(r.getParticipant().getFirstName() + " " + r.getParticipant().getLastName())
                        .correctNumber(r.getCorrectNumber())
                        .emptyNumber(r.getEmptyNumber())
                        .build()
                ).collect(Collectors.toList());

        return resultDtos;
    }
}
