package br.com.rogeriogregorio.blogPessoal.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post_images")
@NamedNativeQuery(
        name = "PostImage.findAllByPostId",
        query = "SELECT * \n"
                + "FROM post_images \n"
                + "WHERE post_id = ?",
        resultClass = PostImage.class
)
public class PostImage {

	@Id
	@Column(name = "url_image")
	@NotBlank
	private String urlImage;
	@ManyToOne(targetEntity = Post.class)
	@JoinColumn(name = "post_id")
	@NotNull
	private Post post;
	
	public PostImage() {
	}

	public PostImage(String urlImage, Post post) {
		super();
		this.urlImage = urlImage;
		this.post = post;
	}

	public String geturlImage() {
		return urlImage;
	}

	public void seturlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Post getpostId() {
		return post;
	}

	public void setpostId(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "PostImage [urlImage=" + urlImage + ", post=" + post + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(urlImage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostImage other = (PostImage) obj;
		return Objects.equals(urlImage, other.urlImage);
	}
}
