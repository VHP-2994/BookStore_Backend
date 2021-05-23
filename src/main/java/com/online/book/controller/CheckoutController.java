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

import com.online.book.entity.Checkout;
import com.online.book.entity.User;
import com.online.book.repository.CheckoutRepository;
import com.online.book.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CheckoutController {

	@Autowired
	CheckoutRepository checkoutRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/saveaddress/{userId}")
	public Optional<Checkout> addNewAddress(@RequestBody Checkout checkoutdetails,@PathVariable (value="userId") int userId) {
		Optional<User> userr = userRepository.findById(userId);
    	User signInUser = userr.get();
    	System.out.println(signInUser);
		 return userRepository.findById(userId).map(user -> {
			 checkoutdetails.setUser(user);
	            return checkoutRepository.save(checkoutdetails);
	        });
	}
	
	@GetMapping("/checkout/{userId}")
    public List<Checkout> getAllCheckoutByUserId(@PathVariable (value = "userId") int userId) {
        return checkoutRepository.findByUserId(userId);
    }
	
	@GetMapping("/checkoutdetails/{Id}")
    public Optional<Checkout> getAllCheckoutByAddressId(@PathVariable (value = "Id") int Id) {
        return checkoutRepository.findById(Id);
    }
	
}
