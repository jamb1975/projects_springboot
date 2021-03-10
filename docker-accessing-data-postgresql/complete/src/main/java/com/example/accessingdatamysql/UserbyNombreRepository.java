package com.example.accessingdatamysql;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserbyNombreRepository extends CrudRepository<Users,String> {
	@Repository
	public interface CityRepository extends CrudRepository<Users, String> {

	    @Query("select u from Users c where c.name like %?1")
	    List<Users> findByNameEndsWith(String chars);
	}

}
