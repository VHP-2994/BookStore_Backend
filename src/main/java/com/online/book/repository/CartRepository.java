package com.online.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.book.entity.AddToCart;

public interface CartRepository extends JpaRepository<AddToCart , Long> {

}
