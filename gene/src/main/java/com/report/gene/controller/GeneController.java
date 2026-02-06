package com.report.gene.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class GeneController {

    @GetMapping("/home")
    public String testSpringboot() {
        return "springboot is running";
    }
    
    
}
