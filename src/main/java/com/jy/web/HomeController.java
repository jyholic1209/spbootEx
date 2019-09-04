package com.jy.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.user.QuestionRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	public QuestionRepository questionRepository;

	@GetMapping("")
	public String welCome(Model model) {
		
		model.addAttribute("questions", questionRepository.findAll());

		return "index";
	}
}
