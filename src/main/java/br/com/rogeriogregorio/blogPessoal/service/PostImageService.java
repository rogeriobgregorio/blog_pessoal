package br.com.rogeriogregorio.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rogeriogregorio.blogPessoal.exception.IllegalArgumentException;
import br.com.rogeriogregorio.blogPessoal.model.PostImage;
import br.com.rogeriogregorio.blogPessoal.repository.PostImageRepository;

@Service
public class PostImageService {

    @Autowired
    private PostImageRepository postImageRep;

    public List<PostImage> findAll() {
        return postImageRep.findAll();
    }

    public List<PostImage> findAllByPostId(Long postId) {
        return postImageRep.findAllByPostId(postId);
    }

    public Optional<PostImage> findById(String urlImage) {
        return postImageRep.findById(urlImage);
    }

    public void save(PostImage postImage) throws IllegalArgumentException {
        postImageRep.save(postImage);
    }

    public void delete(PostImage postImage) throws IllegalArgumentException {
        postImageRep.delete(postImage);
    }
}