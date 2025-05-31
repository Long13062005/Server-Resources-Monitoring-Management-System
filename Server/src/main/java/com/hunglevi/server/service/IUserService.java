package com.hunglevi.server.service;

import com.hunglevi.server.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IUserService {
    // Define methods that the user service should implement
    // For example:
     Users findUserById(Long id);
     Page<Users> findAllUsers(Pageable pageable);
     Users saveUser(Users user);
     void deleteUser(Long id);
}
