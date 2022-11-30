package br.com.rogeriogregorio.blogPessoal.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import br.com.rogeriogregorio.blogPessoal.model.Post;
import br.com.rogeriogregorio.blogPessoal.model.response.Response;
import br.com.rogeriogregorio.blogPessoal.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private WebRequest request;
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/page/{page}")
    public Page<Post> getAll(@PathVariable(value = "page") Integer page) {
		
		Page<Post> pagePosts = postService.findAll(page, 9);
		
		if(!pagePosts.isEmpty()) {
			return pagePosts;
		} else {
			throw new ResourceNotFoundException("posts not found");
		}
    }
	
	@GetMapping("/post/by-id/{postId}")
	public ResponseEntity<Response> getPost(@PathVariable(value = "postId") Long postId) {

		Optional<Post> post = postService.findById(postId);
		
		if(post.isPresent()) {
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, post.get(), request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post not found");
		}
	}
	
    @GetMapping("/post/by-title/{postTitle}/{page}")
    public Page<Post> getPostsByTitle(
    		@PathVariable(value = "postTitle") String postTitle, 
    		@PathVariable(value = "page") Integer page) {
    	
        Page<Post> pagePosts = postService.findAllByTitle(postTitle, page, 9);
        
		if(!pagePosts.isEmpty()) {
			return pagePosts;
		} else {
			throw new ResourceNotFoundException("posts not found");
		}
    }
    
	@GetMapping("/post/by-user-nickname/{postUserNickname}")
	public ResponseEntity<Response> getPostsByUserNickname(@PathVariable(value = "postUserNickname") String postUserNickname) {

		List<Post> listPost = postService.findAllByUserNickname(postUserNickname);
		
		if(!listPost.isEmpty()) {
			return ResponseEntity.ok(new Response(Instant.now(), 200, listPost, request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("posts not found");
		}
	}

	@PostMapping("/post")
	public ResponseEntity<Response> postPost(@Valid @RequestBody Post post) {
		
		if(postService.findById(post.getId()).isEmpty()) {
			postService.save(post);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post inserted successfully", request.getDescription(false)));
		} else {
			throw new ResourceAlreadyPresentException("post already present in the database");
		}
	}

	@PutMapping("/post")
	public ResponseEntity<Response> putPost(@Valid @RequestBody Post post) {
		
		if(postService.findById(post.getId()).isPresent()) {
			postService.save(post);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post updated successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post not found");
		}
	}

	@DeleteMapping("/post")
	public ResponseEntity<Response> deletePost(@Valid @RequestBody Post post) {

		if(postService.findById(post.getId()).isPresent()) {
			postService.delete(post);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post deleted successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post not found");
		}
	}
}
