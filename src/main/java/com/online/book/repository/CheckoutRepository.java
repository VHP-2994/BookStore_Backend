package com.online.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.book.entity.BookReview;
import com.online.book.entity.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer>{

	List<Checkout> findByUserId(int userId);

}
