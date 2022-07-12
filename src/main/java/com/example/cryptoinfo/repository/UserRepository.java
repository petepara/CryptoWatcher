package com.example.cryptoinfo.repository;

import com.example.cryptoinfo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
