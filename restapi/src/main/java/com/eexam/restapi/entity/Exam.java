package com.eexam.restapi.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam implements Serializable {

	private static final long serialVersionUID = -6607723649484623433L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "finish_date")
	private LocalDateTime finishDate;

	@Column(name = "unique_url", unique = true)
	private String uniqueURL;

	@OneToMany(fetch = FetchType.EAGER, 
			   cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_id")
	private List<Question> questions;

    @OneToMany(fetch = FetchType.LAZY,
			   mappedBy = "exam", 
			   cascade = CascadeType.REMOVE)
    private List<Result> results = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, 
			   cascade = {CascadeType.MERGE, CascadeType.REFRESH, 
						  CascadeType.PERSIST})
	@JoinColumn(name = "instructor_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Instructor creator;

	public void addToResult(Result result) {
		if(results == null) {
			results = new ArrayList<>();
		}
        results.add(result);
        result.setExam(this);
    }

}
