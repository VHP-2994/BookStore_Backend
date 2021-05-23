package com.online.book.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Component
@Entity
@Table(name= "addtocart")
public class AddToCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cart_id;
	
	private String cart_bookname;
	private String cart_bookauthor;
	private int cart_bookprice;
	@Lob
	 @NotNull
	private byte[] cart_bookimage;
	private int sub_total;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userfk_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getCart_id() {
		return cart_id;
	}
	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}
	public String getCart_bookname() {
		return cart_bookname;
	}
	public void setCart_bookname(String cart_bookname) {
		this.cart_bookname = cart_bookname;
	}
	public String getCart_bookauthor() {
		return cart_bookauthor;
	}
	public void setCart_bookauthor(String cart_bookauthor) {
		this.cart_bookauthor = cart_bookauthor;
	}
	public int getCart_bookprice() {
		return cart_bookprice;
	}
	public void setCart_bookprice(int cart_bookprice) {
		this.cart_bookprice = cart_bookprice;
	}
	public int getSub_total() {
		return sub_total;
	}
	public void setSub_total(int sub_total) {
		this.sub_total = sub_total;
	}
	
	
	public byte[] getCart_bookimage() {
		return cart_bookimage;
	}
	public void setCart_bookimage(byte[] cart_bookimage) {
		this.cart_bookimage = cart_bookimage;
	}
	public AddToCart(long cart_id, String cart_bookname, String cart_bookauthor, int cart_bookprice,
			byte[] cart_bookimage, int sub_total, User user) {
		super();
		this.cart_id = cart_id;
		this.cart_bookname = cart_bookname;
		this.cart_bookauthor = cart_bookauthor;
		this.cart_bookprice = cart_bookprice;
		this.cart_bookimage = cart_bookimage;
		this.sub_total = sub_total;
		this.user = user;
	}
	public AddToCart() {
		super();
	}
	
	
	
	
}
