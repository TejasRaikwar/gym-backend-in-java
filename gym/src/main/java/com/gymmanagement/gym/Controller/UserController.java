package com.gymmanagement.gym.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gymmanagement.gym.Model.User;
import com.gymmanagement.gym.Model.UserFeedbacks;
import com.gymmanagement.gym.Model.UserReviews;
import com.gymmanagement.gym.Repository.FeedbackRepository;
import com.gymmanagement.gym.Repository.ReviewRepository;
import com.gymmanagement.gym.Repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private FeedbackRepository feedbackRepo;
	@Autowired
	private ReviewRepository reviewRepo;

	@PostMapping("/addMember")
	public ResponseEntity<?> addMember(@RequestBody User user) {
		if (userRepo.findByMobile(user.getMobile()).isPresent()) {
			return ResponseEntity.badRequest().body("Mobile Number already exists.");
		}
		user.setRole("member");
		user.setPassword("fitness143");
		return ResponseEntity.ok(userRepo.save(user));

	}

	@GetMapping("/getMembers")
	public List<User> getMembers() {
		return userRepo.findByRoleNot("admin");
	}

	@DeleteMapping("/deleteMember/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable String id) {
		userRepo.deleteById(id);
		return ResponseEntity.ok("Deleted Successfully");
	}

	@PostMapping("/searchMember")
	public ResponseEntity<?> searchMember(@RequestBody Map<String, String> creds) {
		Long mobile = Long.valueOf(creds.get("mobile"));
		String password = creds.get("password");

		Optional<User> optUser = userRepo.findByMobile(mobile);
		if (optUser.isPresent()) {
			User user = optUser.get();
			if (user.getPassword().equals(password)) {
				return ResponseEntity.ok(user);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password not matched");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}

	@PostMapping("/findMember/{mobile}")
	public ResponseEntity<?> findMember(@PathVariable Long mobile) {
	    Optional<User> userOpt = userRepo.findByMobile(mobile);
	    if (userOpt.isPresent()) {
	        return ResponseEntity.ok(userOpt.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }
	}



	@PostMapping("/saveReview")
	public ResponseEntity<?> saveReview(@RequestBody UserReviews review) {
		return ResponseEntity.ok(reviewRepo.save(review));
	}
	
	@GetMapping("/getReviews")
	public List<UserReviews> getReview(){
		return reviewRepo.findAll();
	}
	

}
