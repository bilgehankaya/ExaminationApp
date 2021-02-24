package com.eexam.restapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "participant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant implements Serializable {

    private static final long serialVersionUID = -878435351036706565L;

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
    
    @OneToMany(fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               mappedBy = "participant")
    private List<Result> results;

    public void addResult(Result result) {
        if(results == null) {
            results = new ArrayList<>();
        }
        results.add(result);
        result.setParticipant(this);
    }

}