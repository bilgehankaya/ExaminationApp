package com.eexam.client.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    
    @NotNull(message = "Username is required")
    @Size(min = 3, message = "Username must be at least 3 characters.")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 3, message = "Password must be at least 3 characters.")
    private String password;

    @NotNull(message = "Password is required")
    @Size(min = 3, message = "Password must be at least 3 characters.")
    private String confirmPassword;

    @NotNull(message = "First name is required")
    @Size(min = 2, message = "First name must be at least 2 characters.")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 2, message = "Last name must be at least 2 characters.")
    private String lastName;

    @NotNull(message = "Last name is required")
    @Size(min = 10, message = "Phone Number must be at least 10 characters.")
    private String phoneNumber;

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Role is required")
    private String role;

}