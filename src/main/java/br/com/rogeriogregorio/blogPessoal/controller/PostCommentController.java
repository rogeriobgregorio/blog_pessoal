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
import br.com.rogeriogregorio.blogPessoal.model.PostComment;
import br.com.rogeriogregorio.blogPessoal.model.response.Response;
import br.com.rogeriogregorio.blogPessoal.service.PostCommentService;

@RestController
@RequestMapping("/api")
public class PostCommentController {
	
	@Autowired
	private WebRequest request;

	@Autowired
	private PostCommentService postCommentService;
	
	@GetMapping("/postComment")
    public ResponseEntity<Response> getAllPostComment() {
		
		List<PostComment> postCommentList = postCommentService.findAll();
		
		if(!postCommentList.isEmpty()) {
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, postCommentList, request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post comments not found");
		}
    }
	
	@GetMapping("/postComment/{postCommentId}")
    public ResponseEntity<Response> getPostComment(@PathVariable(value = "postCommentId") Long postCommentId) {
		
		Optional<PostComment> postComment = postCommentService.findById(postCommentId);
		
		if(postComment.isPresent()) {
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, postComment.get(), request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post comment not found");
		}
    }
	
	@GetMapping("/postComment/comments-by-post-id/{postId}")
    public ResponseEntity<Response> getPostCommentByPostId(@PathVariable(value = "postId") Long postId) {
				
		List<PostComment> postCommentList = postCommentService.findAllByPostId(postId);
		
		if(!postCommentList.isEmpty()) {
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, postCommentList, request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post comments not found");
		}
    }
	
	@PostMapping("/postComment")
    public ResponseEntity<Response> postPostComment(@Valid @RequestBody PostComment postComment) {
		
		if(postCommentService.findById(postComment.getId()).isEmpty()) {
			postCommentService.save(postComment);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post comment inserted successfully", request.getDescription(false)));
		} else {
			throw new ResourceAlreadyPresentException("post comment already present in the database");
		}
    }

	@PutMapping("/postComment")
	public ResponseEntity<Response> putPostComment(@Valid @RequestBody PostComment postComment) {
		
		if(postCommentService.findById(postComment.getId()).isPresent()) {
			postCommentService.save(postComment);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post comment updated successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post comment not found");
		}
    }
	
	@DeleteMapping("/postComment")
	public ResponseEntity<Response> deletePostComment(@Valid @RequestBody PostComment postComment) {
		
		if(postCommentService.findById(postComment.getId()).isPresent()) {
			postCommentService.delete(postComment);
	        return ResponseEntity.ok().body(new Response(Instant.now(), 200, "post comment deleted successfully", request.getDescription(false)));
		} else {
			throw new ResourceNotFoundException("post comment not found");
		}
    }
}
