package com.example.demo;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class SuraApplication {

//	@RequestMapping("/resource")
//	  public Map<String,Object> home() {
//	    Map<String,Object> model = new HashMap<String,Object>();
//	    model.put("id", UUID.randomUUID().toString());
//	    model.put("content", "Hello World");
//	    return model;
//	  }
	
//	@RequestMapping("/user")
//	  public Users user(Users user) {
//	    return user;
//	  }	

	public static void main(String[] args) {
		SpringApplication.run(SuraApplication.class, args);
	}
//
//	@Configuration
//	  @Order(SecurityProperties.BASIC_AUTH_ORDER-10)
//	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	      http
//	        .httpBasic()
//	      .and()
//	        .authorizeRequests()
//	          .antMatchers("/index.html", "/", "/home", "/login").permitAll()
//	          .anyRequest().authenticated();
//	    }
//	  }

	  @Configuration
	    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	        @Override
	        protected void configure(HttpSecurity http) throws Exception {
	            // @formatter:off
	            http
	                .httpBasic().and()
	                .logout().and()
	                .authorizeRequests()
	                    .antMatchers("/index.html", "/", "/home", "/login").permitAll()
	                    .anyRequest().authenticated().and()
	                .csrf()
	                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	            // @formatter:on
	        }
	    }

	    @GetMapping(value = "/{path:[^\\.]*}")
	    public String redirect() {
	        return "forward:/";
	    }

	    @GetMapping("/user")
	    @ResponseBody
	    public Principal user(Principal user) {
	        return user;
	    }

	    @GetMapping("/token")
	    @ResponseBody
	    public Map<String, String> token(HttpSession session) {
	        return Collections.singletonMap("token", session.getId());
	    }

}
