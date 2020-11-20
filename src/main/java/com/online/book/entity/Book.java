package com.online.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long book_id;
	
	@Column(name = "book_title")
	private String book_title;
	
	@Column(name = "book_author")
	private String book_author;
	
	@Column(name = "book_price")
	private int book_price;

	
	 // @OneToOne(mappedBy = "book") private Wishlist wishlist;
	 
    
    
	public Book() {
		super();
	}
	public Book(String book_title, String book_author, int book_price) {
		super();
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_price = book_price;
		//this.wishlist = wishlist;
	}
	public long getBook_id() {
		return book_id;
	}
	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public int getBook_price() {
		return book_price;
	}
	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}

	
	}
