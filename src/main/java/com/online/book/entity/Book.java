package com.online.book.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity(name = "book_details")
public class Book {

	@Id
	private int book_id;
	private String book_title;
	private String book_author;
	private int book_price;
	
	public Book(int book_id, String book_title, String book_author, int book_price) {
		super();
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_price = book_price;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
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

	public Book() {
		super();
	}
	
	
}
