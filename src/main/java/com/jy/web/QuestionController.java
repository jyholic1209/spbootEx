package com.jy.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.user.Question;
import com.jy.user.QuestionRepository;
import com.jy.user.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			return "/user/login";
		}
		return "/qna/form";
	}
	
	@PostMapping("create")
	public String create(HttpSession session, String title, String contents) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			return "/user/login";
		}
		
		User sessionUser = HttpSessionUtil.getUserFormSession(session);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model ) {
		
		model.addAttribute("question", questionRepository.findById(id).get());
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).get());
		return "/qna/updateForm";
		
	}
	
	@PutMapping("/{id}")
	public String updateQuestion(@PathVariable Long id, String title, String contents) {
		
		Question upQuestion = questionRepository.findById(id).get();
		upQuestion.update(title, contents);
		questionRepository.save(upQuestion);
		
		return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		questionRepository.deleteById(id);
		return "redirect:/";
	}

}