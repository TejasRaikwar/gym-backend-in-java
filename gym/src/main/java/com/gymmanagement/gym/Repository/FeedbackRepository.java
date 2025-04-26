
package com.gymmanagement.gym.Repository;

import com.gymmanagement.gym.Model.UserFeedbacks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<UserFeedbacks, String> {

}
