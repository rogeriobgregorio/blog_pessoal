package br.com.rogeriogregorio.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rogeriogregorio.blogPessoal.model.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

	List<PostComment> findByPostId(Long id);
}
