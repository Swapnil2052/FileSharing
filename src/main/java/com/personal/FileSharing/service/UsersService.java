package com.personal.FileSharing.service;

import com.personal.FileSharing.entity.Users;
import com.personal.FileSharing.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users saveUsers(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }
//    public Users findUserByUsername(String username){
//        return usersRepository.findUserByUsername(username);
//    }

//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Users user = usersRepository.findUserByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        return User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole())
//                .build();
//    }
}
