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
import br.com.rogeriogregorio.blogPessoal.model.PostImage;
import br.com.rogeriogregorio.blogPessoal.model.response.Response;
import br.com.rogeriogregorio.blogPessoal.service.PostImageService;

@RestController
@RequestMapping("/api")
public class PostImageController {
	
	@Autowired
	private WebRequest request;

	@Autowired
	private PostImageService postImageService;
	
	@GetMapping("/postImage")
	public ResponseEntity<Response> getAllPostImage() {

		List<PostImage> listPostImage = postImageService.findAll();
		return ResponseEntity.ok(new Response(Instant.now(), 200, listPostImage, request.getDescription(false)));
	}
	
	@GetMapping("/postImage/by-post-id/{postId}")
	public ResponseEntity<Response> getAllPostImageByPostId(@PathVariable(value = "postId") Long postId) {

		List<PostImage> listPostImage = postImageService.findAllByPostId(postId);
		
		if(!listPostImage.isEmpty()) {
			return ResponseEntity.ok(new Response(Instant.now(), 200, listPostImage, request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post images not found");
		}
	}

	@GetMapping("/postImage/{postImageUrlImage}")
	public ResponseEntity<Response> getPostImage(@PathVariable(value = "postImageUrlImage") String postImageUrlImage) {

		Optional<PostImage> postImage = postImageService.findById(postImageUrlImage);
		
		if(postImage.isPresent()) {
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, postImage.get(), request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post image not found");
		}
	}
	
	@PostMapping("/postImage")
	public ResponseEntity<Response> postPostImage(@Valid @RequestBody PostImage postImage) {
		
		if(postImageService.findById(postImage.geturlImage()).isEmpty()) {
			postImageService.save(postImage);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post image inserted successfully", request.getDescription(false)));
		} else {
			throw new ResourceAlreadyPresentException("post image already present in the database");
		}
	}

	@PutMapping("/postImage")
	public ResponseEntity<Response> putPostImage(@Valid @RequestBody PostImage postImage) {
		
		if(postImageService.findById(postImage.geturlImage()).isPresent()) {
			postImageService.save(postImage);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post image updated successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post image not found");
		}
	}

	@DeleteMapping("/postImage")
	public ResponseEntity<Response> deletePostImage(@Valid @RequestBody PostImage postImage) {

		if(postImageService.findById(postImage.geturlImage()).isPresent()) {
			postImageService.delete(postImage);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post image deleted successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post image not found");
		}
	}
}
