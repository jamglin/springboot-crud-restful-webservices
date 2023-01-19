package com.jamg.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamg.springboot.entity.Juser;
import com.jamg.springboot.exception.ResourceNotFoundException;
import com.jamg.springboot.repository.JuserRepository;

@RestController
@RequestMapping("/api/users")
public class JuserController {

	@Autowired
	private JuserRepository juserRepository;
	
	//get all users
	@GetMapping
	public List<Juser> getAllUsers(){
		return this.juserRepository.findAll();
	}
	
	//get user by Id
	@GetMapping("/{id}")
	public Juser getUserById(@PathVariable ("id") long JuserId) {
		return this.juserRepository.findById(JuserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id:" +JuserId));
	}
	
	//Create user
	@PostMapping
	public Juser createUser(@RequestBody Juser juser) {
		return this.juserRepository.save(juser);
	}
	
	//update user
	@PutMapping("{id}")
	public Juser updateUser(@RequestBody Juser juser,@PathVariable("id") long JuserId) {
		Juser existing = this.juserRepository.findById(JuserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id:" +JuserId));
		existing.setFirstName(juser.getFirstName());
		existing.setLastName(juser.getLastName());
		existing.setEmail(juser.getEmail());
		return this.juserRepository.save(existing);
	}
	
	//delete user by id
	@DeleteMapping("{id}")
	public ResponseEntity<Juser> deleteUser(@PathVariable ("id") long JuserId) {
		Juser existing = this.juserRepository.findById(JuserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id:" +JuserId));
		this.juserRepository.delete(existing);
		return ResponseEntity.ok().build();
	}
}
