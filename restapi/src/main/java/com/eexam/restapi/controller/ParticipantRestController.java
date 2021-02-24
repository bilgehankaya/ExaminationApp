package com.eexam.restapi.controller;

import com.eexam.restapi.converter.ParticipantConverter;
import com.eexam.restapi.entity.Exam;
import com.eexam.restapi.entity.Participant;
import com.eexam.restapi.entity.Result;
import com.eexam.restapi.payload.ParticipantDto;
import com.eexam.restapi.payload.ResultRequest;
import com.eexam.restapi.service.ExamService;
import com.eexam.restapi.service.ParticipantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ParticipantRestController {

    @Autowired
    ParticipantService participantService;

    @Autowired
    private ExamService examService;

    @PreAuthorize("hasRole('PARTICIPANT')")
    @GetMapping("/participants")
    public ParticipantDto getParticipant(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Participant participant = participantService.getByUsername(username);
        if(participant == null){
            throw new IllegalStateException("User could not be found");
        }

        return ParticipantConverter.getParticipantDto(participant);
    }


    @PreAuthorize("hasRole('PARTICIPANT')")
    @PostMapping("/participants/saveResult")
    public void saveResult(@RequestBody ResultRequest resultRequest) {
        participantService.saveResult(resultRequest);
    }

    @PreAuthorize("hasRole('PARTICIPANT')")
    @GetMapping("/participants/enroll")
    public void enrollUserToExam(@RequestParam("url") String url) {

        Exam exam = examService.getByUrl(url);
        Participant currentParticipant = participantService.getByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        // create a result object to hold empty initialized result
        Result result = Result.builder().build();

        // assign result to both participant and exam
        currentParticipant.addResult(result);
        exam.addToResult(result);

        participantService.save(currentParticipant);

        // TODO: Check for dates, expiratory

    }
    

}