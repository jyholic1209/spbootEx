package com.jy.web;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
	@GetMapping("/form/{id}")
	public String form(@PathVariable Long id, Model model) {
		
		// springboot 2.x findOne을 사용 못		
		model.addAttribute("user", userRepository.findById(id).get());
		return "/user/updateform";
	}
	
//	@PostMapping("/{id}") 
	@PutMapping("/{id}")
	public String updateUser(@PathVariable Long id, User newUser ) {
		User user = userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "/user/login";
	}
	
	@PostMapping("/loginReq")
	public String loginReq(String userId, String password, HttpSession session) {
		User user = userRepository.findByUId(userId);
		if(user == null) {
			System.out.println("login Err : user is not exist!!");
			return "/user/login";
		}
		if(!password.equals(user.getuPw())){
			System.out.println("login Err : user password is not correct");
			return "/user/login";
		}
		session.setAttribute("user", user);
		System.out.println(user.toString());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
}
