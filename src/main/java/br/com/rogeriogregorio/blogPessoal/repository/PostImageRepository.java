package br.com.rogeriogregorio.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rogeriogregorio.blogPessoal.model.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, String> {
	List<PostImage> findAllByPostId(Long postId);
}
