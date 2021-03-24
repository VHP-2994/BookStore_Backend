package com.online.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.online.book.config.TokenProvider;
import com.online.book.entity.Book;
import com.online.book.entity.LoginUser;
import com.online.book.entity.User;
import com.online.book.repository.UserRepository;
import com.online.book.service.UserService;

@RestController
@CrossOrigin
public class AuthenticationController {
	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public User register(@RequestBody LoginUser loginUser) throws Exception {

    	try {
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
    	}
    	catch(BadCredentialsException e) {
    		throw new Exception("Incorrect username or password");
    	}
    	User user = userRepository.findByUsername(loginUser.getUsername()).get();
		final UserDetails userDetails = userService
				.loadUserByUsername(loginUser.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		user.setToken(token);

		return user;
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUpUser(@RequestBody User user) throws Exception {
    	userRepository.save(user);
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUser() throws Exception {
    	return userRepository.findAll();
    }
    
    @GetMapping("/user/{id}")
	public Optional<User> getBookById(@PathVariable int id) {
	  return userRepository.findById(id);
	}
    
    @PutMapping("/editaccount/{id}")
	public User updateUser(@PathVariable(value="id") int id,@RequestBody User updateduser){
		Optional<User> UserResponse = userRepository.findById(id);
		UserResponse.ifPresent(u -> {
			u.setFirstName(updateduser.getFirstName());
			u.setLastName(updateduser.getLastName());
			u.setUsername(updateduser.getUsername());
		});
		User user = UserResponse.get();
		return userRepository.save(user);
		
	}
    
}
