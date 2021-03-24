package com.online.book.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "bookuser")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "user_firstname")
	private String firstName;
	@Column(name = "user_lastname")
	private String lastName;
	@Column(name = "username")
	private String username;
	@Column(name = "user_password")
	private String user_password;
	@Column(name = "token")
	private String token;
	public int getUser_id() {
		return id;
	}
	public void setUser_id(int user_id) {
		this.id = user_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User(int user_id, String firstName, String lastName, String username, String user_password, String token) {
		super();
		this.id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.user_password = user_password;
		this.token = token;
	}
	public User() {
		super();
	}
	
	
}
