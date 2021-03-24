package com.online.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.book.entity.BookReview;

@Repository
public interface ReviewRepository extends JpaRepository<BookReview , Long>{

	List<BookReview> findByBookId(Long bookId);
    //Optional<BookReview> findByReviewIdAndBookId(Long review_id, Long book_id);
}
