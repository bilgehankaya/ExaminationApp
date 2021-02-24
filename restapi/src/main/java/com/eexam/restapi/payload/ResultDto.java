package com.eexam.restapi.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResultDto {

    private int resultId;
    private int examId;
    private String examTitle;
    private int participantId;
    private String participantFullName;
    private int correctNumber;
    private int incorrectNumber;
    private boolean joined;
    private int emptyNumber;

}
