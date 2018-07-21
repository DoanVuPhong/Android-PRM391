package com.fpt.edu.dao;

import com.fpt.edu.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
