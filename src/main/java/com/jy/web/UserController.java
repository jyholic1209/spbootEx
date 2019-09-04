package com.jy.web;


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
	public String form(@PathVariable Long id, Model model, HttpSession session) {
		System.out.println("/form/id " + session.toString());
		User sessionUser = HttpSessionUtil.getUserFormSession(session);
		if(sessionUser == null) {
			System.out.println("session null");
			return "redirect:/user/login";
		}
		
		if(!id.equals(sessionUser.getId())) {
			throw new IllegalStateException("자기 정보만 수정 가능합니다.");
		}
		
		// springboot 2.x findOne을 사용 못		
		model.addAttribute("user", userRepository.findById(id).get());
		return "/user/updateform";
	}
	
//	@PostMapping("/{id}") 
	@PutMapping("/{id}")
	public String updateUser(@PathVariable Long id, User updateUser, HttpSession session ) {
		// session에서 로그인 상태를 확인함
		System.out.println("putmapping " + session.toString());
		User sessionUser = HttpSessionUtil.getUserFormSession(session);
		if(sessionUser == null) {
			return "redirect:/user/login";
		}
		
		if(!id.equals(sessionUser.getId())) {
			throw new IllegalStateException("자기 정보만 수정 가능합니다.");
		}
		
		User user = userRepository.findById(id).get();
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		System.out.println("login " + session.toString());
		if(session.getAttribute(HttpSessionUtil.USER_SESSION_KEY) == null) {
			return "/user/login";
		} 
		return "redirect:/";
	}
	
	@PostMapping("/loginReq")
	public String loginReq(String userId, String password, HttpSession session) {
		System.out.println("loginReq " + session.toString());
		User user = userRepository.findByUId(userId);
		if(user == null) {
			System.out.println("login Err : user is not exist!!");
			return "/user/login";
		}
		if(!password.equals(user.getuPw())){
			System.out.println("login Err : user password is not correct");
			return "/user/login";
		}
		session.setAttribute(HttpSessionUtil.USER_SESSION_KEY, user);
		System.out.println("loginReq : " + user.toString());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("logout " + session.toString());
		session.removeAttribute(HttpSessionUtil.USER_SESSION_KEY);
		System.out.println("logout");
		return "redirect:/";
	}
}
