package com.personal.FileSharing.service;

import com.personal.FileSharing.entity.Users;
import com.personal.FileSharing.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;
    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=usersRepository.findUserByUsername(username);
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> authorities=new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole()));
                return authorities;
            }

            @Override
            public String getPassword() {
               return user.getPassword();
            }

            @Override
            public String getUsername() {
               return user.getUsername();
            }
        };
    }
}
