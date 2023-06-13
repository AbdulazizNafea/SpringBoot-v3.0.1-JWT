package com.example.jwt_auth.repository;

import com.example.jwt_auth.user.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    Optional<MyUser> findByEmail(String email);
}
