package com.online.book.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.book.entity.AddToCart;
import com.online.book.entity.Book;
import com.online.book.entity.Order;
import com.online.book.entity.User;
import com.online.book.repository.BookRepository;
import com.online.book.repository.CartRepository;
import com.online.book.repository.OrderRepository;
import com.online.book.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	BookRepository bookRepo;
	

	@PostMapping("/saveOrder/{userId}")
	public Optional<List<Order>> addOrders(@PathVariable(value = "userId") int userId) {
		int sum = 0;
		Optional<User> userr = userRepository.findById(userId);
		User signInUser = userr.get();
		
		List<Order> orderlist = new ArrayList<Order>();
		List<AddToCart> cartlist = cartRepository.findByUserId(userId);
		Order order = new Order();
		
		
		
		  for(AddToCart cartt: cartlist) { int price = cartt.getCart_bookprice(); sum =
		  sum + price; cartt.setSub_total(sum);
		  order.setOrder_totalprice(cartt.getSub_total()); }
		 
		for(AddToCart cart: cartlist) {
			
			order.setOrder_bookname(cart.getCart_bookname());
			order.setOrder_author(cart.getCart_bookauthor());
			order.setOrder_price(cart.getCart_bookprice());
			order.setOrder_bookimage(cart.getCart_bookimage());
			order.setOrder_id(UUID.randomUUID().toString());
			order.setCreatedDate(new Date());
			order.setOrderbook_status("Booked");
			orderlist.add(order);
			order = new Order();
			
		}
		System.out.println(signInUser);
		return userRepository.findById(userId).map(user -> {
			//order.setUser(user);
			for(Order or: orderlist) {
				or.setUser(user);
			}
			List<AddToCart> cartItemRemove = cartRepository.findByUserId(userId);
			cartRepository.deleteInBatch(cartItemRemove);
			return orderRepository.saveAll(orderlist);
		});
	}
	
	@GetMapping("/order/{id}")
	public List<Order> getOrderByUserId(@PathVariable int id) {
		return orderRepository.findByUserId(id);
	}
	
	@PutMapping("/canelOrder/{id}")
	public Order findByOrderIdCancelOrder(@PathVariable int id,@RequestBody Order updateOrder) {
		Optional<Order> order = orderRepository.findById(id);
		order.ifPresent(o->{
			o.setOrder_author(updateOrder.getOrder_author());
			o.setOrder_bookimage(updateOrder.getOrder_bookimage());
			o.setOrder_bookname(updateOrder.getOrder_bookname());
			o.setOrder_price(updateOrder.getOrder_price());
			o.setOrderbook_status("cancelled");
		});
		Order ordernew = order.get();
		return orderRepository.save(ordernew);
		
	}
	
	@GetMapping("/orderCancel/{id}")
	public Order findByOrderId(@PathVariable int id) {
		Optional<Order> order = orderRepository.findById(id);
		Order curOrder = order.get();
		return curOrder;
		
	}
	
//	@PostMapping("/sampleSave/{userId}")
//	public Optional<Order> testaddOrders(@PathVariable(value = "userId") int userId, @RequestParam List<Long> id) {
//		
//		Optional<User> userr = userRepository.findById(userId);
//		User signInUser = userr.get();
//		for(Long bookid: id) {
//			Optional<Book> book = bookRepo.findById(bookid);
//			Book boook = book.get();
//			
//		}
//		
//		return null;
//	
//	}
}
