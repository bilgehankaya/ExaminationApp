package com.eexam.restapi.converter;

import com.eexam.restapi.entity.Participant;
import com.eexam.restapi.payload.ParticipantDto;
import com.eexam.restapi.payload.ResultDto;

import java.util.List;

public class ParticipantConverter {

    public static ParticipantDto getParticipantDto(Participant participant){

        List<ResultDto> resultDtoList = ResultConverter.getResultDtoList(participant.getResults());

        ParticipantDto participantDto = ParticipantDto.builder()
                .email(participant.getEmail())
                .id(participant.getId())
                .firstName(participant.getFirstName())
                .lastName(participant.getLastName())
                .username(participant.getUsername())
                .phoneNumber(participant.getPhoneNumber())
                .resultDtos(resultDtoList)
                .build();

        return participantDto;
    }
}
