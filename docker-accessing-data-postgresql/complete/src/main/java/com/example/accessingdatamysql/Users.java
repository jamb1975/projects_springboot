package com.example.accessingdatamysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
//@Table(name="users")
public class Users {
	  @Id
	   // @Column(name = "ID", table = "usuario", unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 22)
	    @GeneratedValue(generator = "SEQ_USERS_ID", strategy = GenerationType.SEQUENCE)
	    @SequenceGenerator(name = "SEQ_USERS_ID", sequenceName = "SEQ_USERS_ID", allocationSize = 1, initialValue = 1)
	   
	private Long id;
	private String name;
	private String email;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
