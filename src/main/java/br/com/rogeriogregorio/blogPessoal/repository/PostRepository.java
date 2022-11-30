package br.com.rogeriogregorio.blogPessoal.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.rogeriogregorio.blogPessoal.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAllByTitle(String title);
	
	@Query("FROM Post p")
    Page<Post> findAll(Pageable pageable);
    @Query("FROM Post p "
            + "WHERE p.title LIKE %:title%")
    Page<Post> findAllByTitle(@Param("title") String title, Pageable pageable);
    List<Post> findAllByUserNickname(String nickname);
}
