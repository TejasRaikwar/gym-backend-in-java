package com.gymmanagement.gym.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	Optional<User> findByMobile(Long mobile);
	List<User> findByRoleNot(String role);
}
