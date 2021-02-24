package com.eexam.restapi.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private int id;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "correct_choice")
	private int correctChoice; 
	
	@OneToMany(fetch = FetchType.EAGER, 
			   cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	private List<Choice> choices;
	
}
