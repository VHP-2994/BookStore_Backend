package com.online.book.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Component
@Entity
@Table(name= "wishlist")
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long wish_id;
	
	private String wish_bookname;
	private String wish_bookauthor;
	private int wish_bookprice;
	
	/*
	 * @JsonIgnore
	 * 
	 * @OneToOne(fetch= FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "book_id") Book book;
	 */
	 

	public long getWish_id() {
		return wish_id;
	}

	public void setWish_id(long wish_id) {
		this.wish_id = wish_id;
	}

	
	/*
	 * public Book getBook() { return book; }
	 * 
	 * public void setBook(Book book) { this.book = book; }
	 */
	 

	public String getWish_bookname() {
		return wish_bookname;
	}

	public void setWish_bookname(String wish_bookname) {
		this.wish_bookname = wish_bookname;
	}

	public String getWish_bookauthor() {
		return wish_bookauthor;
	}

	public void setWish_bookauthor(String wish_bookauthor) {
		this.wish_bookauthor = wish_bookauthor;
	}

	public int getWish_bookprice() {
		return wish_bookprice;
	}

	public void setWish_bookprice(int wish_bookprice) {
		this.wish_bookprice = wish_bookprice;
	}

	public Wishlist(long wish_id, String wish_bookname, String wish_bookauthor, int wish_bookprice) {
		super();
		this.wish_id = wish_id;
		this.wish_bookname = wish_bookname;
		this.wish_bookauthor = wish_bookauthor;
		this.wish_bookprice = wish_bookprice;
		//this.book = book;
	}

	public Wishlist() {
		super();
	}

	
	
	

}
