package br.com.rogeriogregorio.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rogeriogregorio.blogPessoal.exception.IllegalArgumentException;
import br.com.rogeriogregorio.blogPessoal.model.PostComment;
import br.com.rogeriogregorio.blogPessoal.repository.PostCommentRepository;

@Service
public class PostCommentService {

    @Autowired
    private PostCommentRepository postCommentRep;

    public List<PostComment> findAll() {
        return postCommentRep.findAll();
    }

    public List<PostComment> findAllByPostId(Long id) {
        return postCommentRep.findByPostId(id);
    }

    public Optional<PostComment> findById(Long id) {
        return postCommentRep.findById(id);
    }

    public void save(PostComment postComment) throws IllegalArgumentException {
        postCommentRep.save(postComment);
    }

    public void delete(PostComment postComment) throws IllegalArgumentException {
        postCommentRep.delete(postComment);
    }
}