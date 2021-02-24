package com.eexam.restapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "instructor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor implements Serializable {

    private static final long serialVersionUID = 4385187542602529772L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;

    @Column(unique = true)
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;
    
    @Column
    private String email;

    @Column
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, 
               fetch = FetchType.LAZY, 
               mappedBy = "creator")
    private List<Exam> createdExams;

    public void addToCreatedExam(Exam exam) {
        if(createdExams == null) {
            createdExams = new ArrayList<>();
        }
        createdExams.add(exam);
        exam.setCreator(this);
    }
    
}