package com.eexam.restapi.entity;

import javax.persistence.*;

import com.eexam.restapi.model.EnumRole;

import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column
    private EnumRole name;

}
