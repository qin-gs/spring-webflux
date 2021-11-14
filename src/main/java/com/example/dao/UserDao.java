package com.example.dao;

import com.example.bean.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends R2dbcRepository<User, String> {
}
