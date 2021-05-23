package com.online.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.online.book.entity.Book;
import com.online.book.entity.Wishlist;
import com.online.book.repository.BookRepository;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories("com.online.book.repository")
@EnableJpaAuditing()
public class BookApplication{

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
