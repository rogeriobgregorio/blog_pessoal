package br.com.rogeriogregorio.blogPessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rogeriogregorio.blogPessoal.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}