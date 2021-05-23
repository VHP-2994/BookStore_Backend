package com.online.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.book.entity.AddToCart;
import com.online.book.entity.Book;
import com.online.book.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long>{

	List<Wishlist> findByUserId(int userId);

}
