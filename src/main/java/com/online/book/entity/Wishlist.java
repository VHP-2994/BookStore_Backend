package com.online.book.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


@Component
@Entity
@Table(name= "wishlist")
public class Wishlist {

	@Id
	private long wish_id;
	
	private String wish_bookname;
	private String wish_bookauthor;
	private int wish_bookprice;
	
	@Lob
	 @NotNull
	private byte[] wish_bookimage;
	 

	public long getWish_id() {
		return wish_id;
	}

	public void setWish_id(long wish_id) {
		this.wish_id = wish_id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userfk_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

	
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
	

	public byte[] getWish_bookimage() {
		return wish_bookimage;
	}

	public void setWish_bookimage(byte[] wish_bookimage) {
		this.wish_bookimage = wish_bookimage;
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Wishlist(long wish_id, String wish_bookname, String wish_bookauthor, int wish_bookprice,
			byte[] wish_bookimage, User user) {
		super();
		this.wish_id = wish_id;
		this.wish_bookname = wish_bookname;
		this.wish_bookauthor = wish_bookauthor;
		this.wish_bookprice = wish_bookprice;
		this.wish_bookimage = wish_bookimage;
		this.user = user;
	}

	public Wishlist() {
		super();
	}


}
