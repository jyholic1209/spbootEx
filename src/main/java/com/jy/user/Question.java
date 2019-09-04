package com.jy.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
//	private String writer;
	private String title;
	private String contents;
	private LocalDateTime createTime;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createTime = LocalDateTime.now();
	}
	
	private String getFormattedCreateDate() {
		if(createTime == null) {
			return "";
		}
		return createTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
		this.createTime = LocalDateTime.now();
	}
}
