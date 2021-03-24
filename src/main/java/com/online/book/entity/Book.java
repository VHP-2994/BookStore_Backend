package com.online.book.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

@Component
@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "book_title")
	private String book_title;
	
	@Column(name = "book_author")
	private String book_author;
	
	@Column(name = "book_price")
	private int book_price;

	@Lob
	 @NotNull
	private byte[] bookimage;
	

	 // @OneToOne(mappedBy = "book") private Wishlist wishlist;
	 
    
    
	public Book() {
		super();
	}
	
	public Book(long book_id, String book_title, String book_author, int book_price,byte[] bookimage) {
		super();
		this.id = book_id;
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_price = book_price;
		this.bookimage = bookimage;
	}

	public long getBook_id() {
		return id;
	}
	public void setBook_id(long book_id) {
		this.id = book_id;
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

	public byte[] getBookimage() {
		return bookimage;
	}

	public void setBookimage(byte[] bookimage) {
		this.bookimage = bookimage;
	}

	
	}
