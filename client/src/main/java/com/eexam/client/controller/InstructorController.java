package com.eexam.client.controller;

import com.eexam.client.dto.InstructorDto;
import com.eexam.client.service.InstructorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inst")
public class InstructorController {
    
    @Autowired
    InstructorService instructorService;

    @GetMapping("/home")
    public String showInstHome(Model model) {

        System.out.println("Hello");

        InstructorDto instructorDto = instructorService.getInstructor();

        model.addAttribute("instructorDto", instructorDto);

        return "inst-home";
    }
    
    /*
    @GetMapping("/exam")
    public String createExam(Model model) {
        model.addAttribute("examdto", new Exam());
        return "create-exam";
    }

    @PostMapping("process")
    public void processExam(@ModelAttribute("modelExam") Exam exam) {
        System.out.println(exam.getTitle() + " " + exam.getQuestions());
    }
    */

}