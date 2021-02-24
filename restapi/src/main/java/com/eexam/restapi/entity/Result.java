package com.eexam.restapi.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result implements Serializable {

    private static final long serialVersionUID = 7698183198120955330L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;

    @Column(name = "correct_number")
    private int correctNumber;

    @Column(name = "incorrect_number")
    private int incorrectNumber;

    @Column(name = "empty_number")
    private int emptyNumber;

    @Column(name = "isJoined")
    private boolean isJoined;

    @ManyToOne
    @JoinColumn(name="exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name="participant_id")
    private Participant participant;
    
}