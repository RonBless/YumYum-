package iob.data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name = "Users")
public class UserEntity {
	private UserRole role;
	private String username;
	private String avatar;
	private String domainEmail;
	
	public UserEntity(UserRole role, String username, String avatar, String domainEmail) {
		this.role = role;
		this.username = username;
		this.avatar = avatar;
		this.domainEmail = domainEmail;
	}
	public UserEntity() {
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "Role")
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}

	@Column(name = "Username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Avatar")
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	@Id
	public String getDomainEmail() {
		return domainEmail;
	}
	public void setDomainEmail(String domainEmail) {
		this.domainEmail = domainEmail;
	}
	
}
