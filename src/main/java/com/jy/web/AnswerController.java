package com.jy.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jy.user.Answer;
import com.jy.user.AnswerRepository;
import com.jy.user.Question;
import com.jy.user.QuestionRepository;
import com.jy.user.User;

@Controller
@RequestMapping("/questions/{questionId}/answer")
public class AnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String createAnswer(@PathVariable Long questionId, HttpSession session, String contents) {
		if(!HttpSessionUtil.isLoginUser(session)) {
			return "user/login";
		}
		User answerUser = HttpSessionUtil.getUserFormSession(session);
		Question question = questionRepository.findById(questionId).get();
		Answer newAnswer = new Answer(answerUser, contents, question);
		answerRepository.save(newAnswer);
		
		return String.format("redirect:/questions/%d", questionId);
	}

}
