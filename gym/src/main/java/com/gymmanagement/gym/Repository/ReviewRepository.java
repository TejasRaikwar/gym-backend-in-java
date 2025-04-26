package com.gymmanagement.gym.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gymmanagement.gym.Model.UserReviews;

@Repository
public interface ReviewRepository extends MongoRepository<UserReviews,String>{
	
}
