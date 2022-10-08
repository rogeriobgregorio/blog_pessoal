package br.com.rogeriogregorio.blogPessoal.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "nickname")
	@NotBlank
	private String nickname;
	@Column(name = "email")
	@NotBlank
	private String email;
	@Column(name = "profile_image_url")
	@NotBlank
	private String profileImageUrl;
	@Column(name = "is_adm")
	@NotNull
	private Integer isAdm;

	public User() {
	}

	public User(String nickname, String email, String profileImageUrl, Integer isAdm) {
		super();
		this.nickname = nickname;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
		this.isAdm = isAdm;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public Integer getIsAdm() {
		return isAdm;
	}

	public void setIsAdm(Integer isAdm) {
		this.isAdm = isAdm;
	}

	@Override
	public String toString() {
		return "User [nickname=" + nickname + ", email=" + email + ", profileImageUrl=" + profileImageUrl + ", isAdm="
				+ isAdm + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(nickname, other.nickname);
	}
}

