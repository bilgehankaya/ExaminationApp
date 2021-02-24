package com.eexam.client.service;

import com.eexam.client.dto.ExamDto;

public interface ExamService {
    public ExamDto getExamById(int id);
    public ExamDto getExamByUrl(String url);
}