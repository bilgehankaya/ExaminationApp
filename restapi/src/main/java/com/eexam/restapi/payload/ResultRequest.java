package com.eexam.restapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultRequest {

    private int id;
    private int correctNumber;
    private int incorrectNumber;
    private int emptyNumber;
    private boolean isJoined;
    private int examId;
    private int participantId;

}