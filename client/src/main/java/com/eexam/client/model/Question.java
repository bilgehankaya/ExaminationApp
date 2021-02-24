package com.eexam.client.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Question {
    
	private int id;
	private int number;
	private int point;
	private String content;
	private int correctChoice; 
	private List<Choice> choices = new ArrayList<>();
    
}