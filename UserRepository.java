package com.example.Registerform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Registerform.model.User;

public interface UserRepository extends JpaRepository<User,Long >{


}
