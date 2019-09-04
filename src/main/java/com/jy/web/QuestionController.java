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
	
	public boolean hasPermision(HttpSession session, Question question) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요합니다");
		}
		
		User loginUser = HttpSessionUtil.getUserFormSession(session);
		if(!question.isSameWriter(loginUser)) {
			throw new IllegalStateException("작성자가 쓴 글만 수정, 삭제가 가능합니다.");
		}
		return true;
	}
	
	@GetMapping("/form")
	public String form(HttpSession session, Model model) {
		if(!HttpSessionUtil.isLoginUser(session)) {	
			model.addAttribute("errorMsg", "먼저 로그인 해 주세요");
			return "/user/login";
		}
		return "/qna/form";
	}
	
	@PostMapping("create")
	public String create(HttpSession session, String title, String contents, Model model) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			model.addAttribute("errorMsg", "먼저 로그인 해 주세요");
			return "/user/login";
		}
		
		User sessionUser = HttpSessionUtil.getUserFormSession(session);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model, HttpSession session ) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			model.addAttribute("errorMsg", "먼저 로그인 해 주세요");
			return "/user/login";
		}
		
		model.addAttribute("question", questionRepository.findById(id).get());
		
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String update(@PathVariable Long id, Model model, HttpSession session) {
		try {
			Question question = questionRepository.findById(id).get();
			if(hasPermision(session, question)) {
				model.addAttribute("question", question);
			}
			return "/qna/updateForm";				
			
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "/user/login";
		}
	}
	
	@PutMapping("/{id}")
	public String updateQuestion(@PathVariable Long id, String title, String contents, HttpSession session) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			return "/user/login";
		}
	
		Question question = questionRepository.findById(id).get();
		User loginUser = HttpSessionUtil.getUserFormSession(session);
		if (!question.isSameWriter(loginUser)) {
			return "/user/login";
		}
		
		question.update(title, contents);
		questionRepository.save(question);
		
		return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			return "/user/login";
		}
	
		Question question = questionRepository.findById(id).get();
		User loginUser = HttpSessionUtil.getUserFormSession(session);
		if (!question.isSameWriter(loginUser)) {
			return "/user/login";
		}
		questionRepository.deleteById(id);
		return "redirect:/";
	}

}
