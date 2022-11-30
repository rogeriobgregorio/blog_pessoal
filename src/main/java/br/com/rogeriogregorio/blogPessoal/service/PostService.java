package br.com.rogeriogregorio.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.rogeriogregorio.blogPessoal.exception.IllegalArgumentException;
import br.com.rogeriogregorio.blogPessoal.model.Post;
import br.com.rogeriogregorio.blogPessoal.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRep;

    public Page<Post> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");

        return postRep.findAll(pageRequest);
    }

    public Optional<Post> findById(Long id) {
        return postRep.findById(id);
    }

    public List<Post> findAllByUserNickname(String nickname) {
        return postRep.findAllByUserNickname(nickname);
    }

    public Page<Post> findAllByTitle(String title, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "id");

        return postRep.findAllByTitle(title, pageRequest);
    }

    public void save(Post post) throws IllegalArgumentException {
        postRep.save(post);
    }

    public void delete(Post post) throws IllegalArgumentException {
        postRep.delete(post);
    }
}
