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
@Table(name = "posts")
@NamedNativeQuery(
        name = "Post.findAllByUserNickname",
        query = "SELECT pst.id, pst.title, pst.content_url, pst.user_nickname \n"
        		+ "FROM posts pst, users usr \n"
        		+ "WHERE pst.user_nickname = usr.nickname \n"
        		+ "AND usr.nickname = ?",
        resultClass = Post.class
)
public class Post {

	@Id
	@Column(name = "id")
	@NotNull
	private Long id;
	@Column(name = "title")
	@NotBlank
	private String title;
	@Column(name = "content_url")
	@NotBlank
	private String contentUrl;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_nickname")
	@NotNull
	private User user;

	public Post() {
	}

	public Post(Long id, String title, String contentUrl, User user) {
		super();
		this.id = id;
		this.title = title;
		this.contentUrl = contentUrl;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", contentUrl=" + contentUrl + ", user=" + user + "]";
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
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
}