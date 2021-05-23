package com.online.book.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.online.book.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByUserId(int id);
	
	@Modifying
	@Query("update Order o set o.orderbook_status = ?1 where o.id = ?2")
	String setOrderStatusByOrderID(String status, String id);

}
