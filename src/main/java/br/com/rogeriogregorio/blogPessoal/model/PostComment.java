package br.com.rogeriogregorio.blogPessoal.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "post_comments")
public class PostComment {

	@Id
	@Column(name = "id")
	@NotNull
	private Long id;
	@Column(name = "content")
	@NotBlank
	private String content;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_nickname")
	@NotNull
	private User user;
	@ManyToOne(targetEntity = Post.class)
	@JoinColumn(name = "post_id")
	@NotNull
	private Post post;

	public PostComment() {
	}

	public PostComment(Long id, String content, User user, Post post) {
		super();
		this.id = id;
		this.content = content;
		this.user = user;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "PostComment [id=" + id + ", content=" + content + ", user=" + user + ", post="
				+ post + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostComment other = (PostComment) obj;
		return Objects.equals(id, other.id);
	}
}
