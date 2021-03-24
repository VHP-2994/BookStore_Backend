package com.online.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.online.book.entity.BookReview;
import com.online.book.entity.User;
import com.online.book.repository.BookRepository;
import com.online.book.repository.ReviewRepository;
import com.online.book.repository.UserRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
//	@GetMapping("/book/{bookId}/review")
//    public Page<BookReview> getAllCommentsByPostId(@PathVariable (value = "bookId") Long postId,
//                                                Pageable pageable) {
//        return reviewRepository.findByBookId(postId, pageable);
//    }
	
	@GetMapping("/book/{bookId}/review")
    public List<BookReview> getAllCommentsByPostId(@PathVariable (value = "bookId") Long postId) {
        return reviewRepository.findByBookId(postId);
    }

    @PostMapping("/book/{bookId}/review/{userId}")
    public Optional<BookReview> createComment(@PathVariable (value = "bookId") Long postId,
                                  @RequestBody BookReview review,@PathVariable (value="userId") int userId) {
    	Optional<User> user = userRepository.findById(userId);
    	User signInUser = user.get();
        return bookRepository.findById(postId).map(post -> {
            review.setBook(post);
            review.setUser(signInUser);
            review.setReviewer_fname(signInUser.getFirstName());
            review.setReviewer_lname(signInUser.getLastName());
            return reviewRepository.save(review);
        });
    }
	
}
