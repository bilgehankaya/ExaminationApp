package com.eexam.restapi.payload;

import com.eexam.restapi.entity.Question;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDto {

    private int id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String uniqueURL;
    private int creatorId;
    private List<Question> questions;
    private List<ResultDto> results;

}