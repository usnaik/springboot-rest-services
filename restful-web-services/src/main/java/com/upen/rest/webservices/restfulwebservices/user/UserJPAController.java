package com.upen.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping ("/jpa/users")
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Optional<User> findAUser(@PathVariable Integer id){
//	public Resource<User> findAUser(@PathVariable Integer id){
		
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) 
			throw new UserNotFoundException("id : " + id);

//		Resource<User> resource = new Resource<User>(userOptional.get());
//		// used HATEOAS to add link to retrieve all-users. 
//		ControllerLinkBuilder linkTo =
//				linkTo(methodOn (this.getClass()).findAllUsers());
//		
//		resource.add(linkTo.withRel("all-users"));		
//		return resource;
		
		return userOptional;
	}
	
	@PostMapping ("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		// Return the location path where the user is created
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteById(@PathVariable Integer id){
		userRepository.deleteById(id); 
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> findAUserPosts(@PathVariable Integer id){
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) 
			throw new UserNotFoundException("id : " + id);
 
		return userOptional.get().getPost();
	}

	@PostMapping ("/jpa/users/{id}/posts")
	public ResponseEntity<User> addUserPosts(@PathVariable Integer id, @Valid @RequestBody Post post) {

		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) 
			throw new UserNotFoundException("id : " + id);
	
		post.setUser(userOptional.get());
		postRepository.save(post);
		
		// Return the location path where the user is created
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
