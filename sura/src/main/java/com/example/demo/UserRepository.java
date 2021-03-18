package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
	Users save(Users user);
	void delete(Users user);
	@Query("select u from Users u where u.username=?1")
    Users findByNameEndsWith(String chars);
    
}
