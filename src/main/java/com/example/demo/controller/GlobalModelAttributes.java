package com.example.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalModelAttributes {
	
	@ModelAttribute("uri")
    public String navBarState(HttpServletRequest request) {
        return request.getRequestURI();
    }   
}
