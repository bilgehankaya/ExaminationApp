package com.eexam.client.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.eexam.client.model.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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