package com.eexam.restapi.payload;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantDto {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<ResultDto> resultDtos;

}