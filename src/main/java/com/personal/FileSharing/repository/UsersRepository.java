package com.personal.FileSharing.repository;

import com.personal.FileSharing.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findUserByUsername(String username);
}
