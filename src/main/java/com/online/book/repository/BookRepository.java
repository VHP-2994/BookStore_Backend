package com.online.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.book.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	

}
