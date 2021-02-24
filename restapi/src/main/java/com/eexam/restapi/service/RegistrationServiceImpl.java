package com.eexam.restapi.service;

import java.util.HashSet;
import java.util.Set;

import com.eexam.restapi.dao.RoleDAO;
import com.eexam.restapi.dao.UserDAO;
import com.eexam.restapi.entity.*;
import com.eexam.restapi.model.EnumRole;
import com.eexam.restapi.payload.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    ParticipantService participantService;

    @Autowired
    InstructorService instructorService;

    @Autowired
	PasswordEncoder encoder;

    @Override
    public MessageResponse register(SignupRequest signupRequest) {
        if(userDAO.existsByUsername(signupRequest.getUsername())) {

            return  MessageResponse.builder()
                                   .status(HttpStatus.CONFLICT.value())
                                   .message("Username is already taken!")
                                   .timeStamp(System.currentTimeMillis())
                                   .build();
        }

        Set<Role> roles = new HashSet<>();

        if(signupRequest.getRole() != null) {
            switch (signupRequest.getRole()) {
                case "PARTICIPANT":

                    Participant newParticipant = Participant.builder()
                                                            .username(signupRequest.getUsername())
                                                            .firstName(signupRequest.getFirstName())
                                                            .lastName(signupRequest.getLastName())
                                                            .email(signupRequest.getEmail())
                                                            .phoneNumber(signupRequest.getPhoneNumber())
                                                            .build();
                    participantService.save(newParticipant);
 
                    Role partRole = roleDAO.findByName(EnumRole.ROLE_PARTICIPANT)
                                           .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(partRole);
                    break;
                
                case "INSTRUCTOR":
                
                    Instructor newInstructor = Instructor.builder()
                                                         .username(signupRequest.getUsername())
                                                         .firstName(signupRequest.getFirstName())
                                                         .lastName(signupRequest.getLastName())
                                                         .email(signupRequest.getEmail())
                                                         .phoneNumber(signupRequest.getPhoneNumber())
                                                         .build();
                    instructorService.save(newInstructor);

                    Role instRole = roleDAO.findByName(EnumRole.ROLE_INSTRUCTOR)
                                           .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(instRole);
                    break;
            
                default:
                    return MessageResponse.builder()
                                          .status(HttpStatus.CONFLICT.value())
                                          .message("Role not found!")
                                          .timeStamp(System.currentTimeMillis())
                                          .build();
            }
        }

        User user = User.builder()
                        .username(signupRequest.getUsername())
                        .password(encoder.encode(signupRequest.getPassword()))
                        .email(signupRequest.getEmail())
                        .roles(roles)
                        .build();
        
        userDAO.save(user);                        

        return MessageResponse.builder()
                              .status(HttpStatus.OK.value())
                              .message("User registered successfully!")
                              .timeStamp(System.currentTimeMillis())
                              .build();
    }

}