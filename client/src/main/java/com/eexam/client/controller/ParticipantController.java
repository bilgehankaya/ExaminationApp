package com.eexam.client.controller;

import com.eexam.client.dto.ExamDto;
import com.eexam.client.dto.ParticipantDto;
import com.eexam.client.service.ExamService;
import com.eexam.client.service.ParticipantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/part")
public class ParticipantController {
    
    @Autowired
    ParticipantService participantService;

    @Autowired
    ExamService examService;

    @GetMapping("/home")
    public String showPartHome(Model model) {

        ParticipantDto participantDto = participantService.getParticipant();

        model.addAttribute("participantDto", participantDto);
        return "part-home";

    }

    @GetMapping("/start/{examId}")
    public void getExamById(@PathVariable String examId) {
        System.out.println("ExamId: " + examId);
    }

    @GetMapping("/enroll")
    public String enrollExam(@RequestParam("url") String url) {
        ExamDto examDto = examService.getExamByUrl(url);
        
        if (examDto != null) { participantService.enrollUserToExam(url); }

        return "redirect:home"; 
    }

}