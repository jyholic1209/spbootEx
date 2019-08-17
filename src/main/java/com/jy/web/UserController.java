package com.jy.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.user.User;
import com.jy.user.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
//	private ArrayList<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/create")
	public String create(User strUser, Model model) {
		
		model.addAttribute("user", strUser);
	//	users.add(strUser);
	//	System.out.println(strUser.toString());
		
		userRepository.save(strUser);
		return "redirect:/user/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/form")
	public String form() {
		
		return "/user/form";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "/user/login";
	}
}
