package br.com.rogeriogregorio.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rogeriogregorio.blogPessoal.exception.IllegalArgumentException;
import br.com.rogeriogregorio.blogPessoal.model.User;
import br.com.rogeriogregorio.blogPessoal.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRep;

    public List<User> findAll() {
        return userRep.findAll();
    }

    public Optional<User> findById(String nickname) {
        return userRep.findById(nickname);
    }

    public void save(User user) throws IllegalArgumentException {
        userRep.save(user);
    }

    public void delete(User user) throws IllegalArgumentException {
        userRep.delete(user);
    }
}
