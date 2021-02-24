package com.eexam.client.dto;

import java.util.ArrayList;
import java.util.List;

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
public class User {

    private int id;
    private String username;
    private String email;
    private String jwt;
    @Builder.Default
	private List<String> roles = new ArrayList<>();

}