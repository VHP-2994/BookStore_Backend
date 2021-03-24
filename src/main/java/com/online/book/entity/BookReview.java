package com.online.book.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name= "bookreview")
public class BookReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long review_id;
	
	private int review_star;
	private String review_comment;
	private String reviewer_fname;
	private String reviewer_lname;
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "bookfk_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Book book;
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "userfk_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private User user;
	
	public long getReview_id() {
		return review_id;
	}
	public void setReview_id(long review_id) {
		this.review_id = review_id;
	}
	public int getReview_star() {
		return review_star;
	}
	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}
	public String getReview_comment() {
		return review_comment;
	}
	public void setReview_comment(String review_comment) {
		this.review_comment = review_comment;
	}
	
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getReviewer_fname() {
		return reviewer_fname;
	}
	public void setReviewer_fname(String reviewer_fname) {
		this.reviewer_fname = reviewer_fname;
	}
	
	
	public String getReviewer_lname() {
		return reviewer_lname;
	}
	public void setReviewer_lname(String reviewer_lname) {
		this.reviewer_lname = reviewer_lname;
	}
	
	
	public BookReview(long review_id, int review_star, String review_comment,Book book,User user,String reviewer_fname,String reviewer_lname) {
		super();
		this.review_id = review_id;
		this.review_star = review_star;
		this.review_comment = review_comment;
		this.book = book;
		this.user =user;
		this.reviewer_fname = reviewer_fname;
		this.reviewer_lname = reviewer_lname;
	}
	public BookReview() {
		super();
	}
	
	
}
