package br.com.rogeriogregorio.blogPessoal.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.context.request.WebRequest;

import br.com.rogeriogregorio.blogPessoal.exception.ResourceAlreadyPresentException;
import br.com.rogeriogregorio.blogPessoal.exception.ResourceNotFoundException;
import br.com.rogeriogregorio.blogPessoal.model.User;
import br.com.rogeriogregorio.blogPessoal.model.response.Response;
import br.com.rogeriogregorio.blogPessoal.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private WebRequest request;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
    public ResponseEntity<Response> getAllUser() {
		
		List<User> listUser = userService.findAll();
        return ResponseEntity.ok().body(new Response(Instant.now(), 200, listUser, request.getDescription(false)));
    }
	
	@GetMapping("/user/{userNickname}")
    public ResponseEntity<Response> getUser(@PathVariable(value = "userNickname") String userNickname) {
		
		Optional<User> user = userService.findById(userNickname);
		
		if(user.isPresent()) {
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, user.get(), request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("user not found");
		}
    }
	
	@PostMapping("/user")
    public ResponseEntity<Response> postUser(@Valid @RequestBody User user) {
		
		if(userService.findById(user.getNickname()).isEmpty()) {
			userService.save(user);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "user inserted successfully", request.getDescription(false)));
		} else {
			throw new ResourceAlreadyPresentException("user already present in the database");
		}
    }

	@PutMapping("/user")
	public ResponseEntity<Response> putUser(@Valid @RequestBody User user) {
		
		if(userService.findById(user.getNickname()).isPresent()) {
			userService.save(user);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "user updated successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("user not found");
		}
    }
	
	@DeleteMapping("/user")
	public ResponseEntity<Response> deleteUser(@Valid @RequestBody User user) {
		
		if(userService.findById(user.getNickname()).isPresent()) {
			userService.delete(user);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "user deleted successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("user not found");
		}
    }
}
