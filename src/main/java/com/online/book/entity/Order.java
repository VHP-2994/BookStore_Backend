package com.online.book.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.book.generator.DatePrefixedSequenceIdGenerator;
import com.sun.istack.NotNull;

@Component
@Entity
@Table(name = "order_book")
@EntityListeners(AuditingEntityListener.class)
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid", strategy = "uuid")
	    private String order_id;

	@Column(name = "order_bookname")
	private String order_bookname;
	@Column(name = "order_author")
	private String order_author;
	@Column(name = "order_price")
	private int order_price;
	
	@Lob
	 @NotNull
	private byte[] order_bookimage;

	@Column(name = "orderbook_status")
	private String orderbook_status;

	@Column(name = "order_totalprice")
	private int order_totalprice;
	
	 @CreatedDate
	    @Column(name = "created_date")
	    private Date createdDate;
	 
	 
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userfk_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_bookname() {
		return order_bookname;
	}

	public void setOrder_bookname(String order_bookname) {
		this.order_bookname = order_bookname;
	}

	public String getOrder_author() {
		return order_author;
	}

	public void setOrder_author(String order_author) {
		this.order_author = order_author;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}

	public byte[] getOrder_bookimage() {
		return order_bookimage;
	}

	public void setOrder_bookimage(byte[] order_bookimage) {
		this.order_bookimage = order_bookimage;
	}

	public String getOrderbook_status() {
		return orderbook_status;
	}

	public void setOrderbook_status(String orderbook_status) {
		this.orderbook_status = orderbook_status;
	}

	public int getOrder_totalprice() {
		return order_totalprice;
	}

	public void setOrder_totalprice(int order_totalprice) {
		this.order_totalprice = order_totalprice;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Order(int id, String order_id, String order_bookname, String order_author, int order_price,
			byte[] order_bookimage, String orderbook_status, int order_totalprice, Date createdDate, User user) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.order_bookname = order_bookname;
		this.order_author = order_author;
		this.order_price = order_price;
		this.order_bookimage = order_bookimage;
		this.orderbook_status = orderbook_status;
		this.order_totalprice = order_totalprice;
		this.createdDate = createdDate;
		this.user = user;
	}

	public Order() {
		super();
	}

	
}
