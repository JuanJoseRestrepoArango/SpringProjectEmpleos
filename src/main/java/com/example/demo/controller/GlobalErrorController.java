package com.example.demo.controller;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GlobalErrorController implements ErrorController{
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request,Model model) {
		Integer code = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("jakarta.servlet.error.exception");
		
		model.addAttribute("status",code);
		model.addAttribute("error", exception == null ? "N/A" : exception.getMessage());
		return "error";
		
	}
}
