package com.example.accessingdatamysql;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.Users;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<Users, Integer> {
	Users save(Users user);
	void delete(Users user);
	@Query("select u from Users u where u.name like %?1")
    List<Users> findByNameEndsWith(String chars);
}
