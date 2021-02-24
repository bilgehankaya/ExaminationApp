package com.eexam.client.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Exam {

	private int id;
	private String title;
	private LocalDateTime startDate;
	private LocalDateTime finishDate;
	private List<Question> questions = new ArrayList<>();

}