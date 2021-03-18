package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
    {
        Users user = userRepository.findByNameEndsWith(username);
         
        if (user == null) 
        {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new SuraUserDetails(user);
    }
 
}
