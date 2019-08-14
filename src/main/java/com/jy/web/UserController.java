package com.jy.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jy.user.User;

@Controller
public class UserController {
	
	private ArrayList<User> users = new ArrayList<User>();
	
	@PostMapping("/create")
	public String create(User strUser, Model model) {
		
		model.addAttribute("user", strUser);
		users.add(strUser);
	//	System.out.println(strUser.toString());
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", users);
		return "userlist";
	}
}
