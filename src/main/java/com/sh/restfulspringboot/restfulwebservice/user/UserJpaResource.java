package com.sh.restfulspringboot.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sh.restfulspringboot.restfulwebservice.post.Post;
import com.sh.restfulspringboot.restfulwebservice.post.PostRepository;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@GetMapping("/jpa/users")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException(String.format("User with id %s is not available", id));
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
		User savedUser = userRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedUser);
	}

	@DeleteMapping("/jpa/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		userRepo.deleteById(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getUserPosts(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException(String.format("User with id %s is not available", id));
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException(String.format("User with id %s is not available", id));
		User foundUser = user.get();
		post.setUser(foundUser);
		Post savedPost = postRepo.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedPost);
	}
}
