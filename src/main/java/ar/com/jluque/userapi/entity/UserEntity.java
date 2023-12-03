package ar.com.jluque.userapi.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_INFO")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USR_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "USR_NAME", unique = false, nullable = false)
	private String name;

	@Column(name = "USR_EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "USR_PASSWORD", unique = false, nullable = false)
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<PhoneEntity> phones;

	@Column(name = "USR_CREATED", unique = false, nullable = true)
	private LocalDateTime created;

	@Column(name = "USR_MODIFIED", unique = false, nullable = true)
	private LocalDateTime modified;

	@Column(name = "USR_LAST_LOGIN", unique = false, nullable = true)
	private LocalDateTime lastLogin;

	@Column(name = "USR_TOKEN", unique = false, nullable = true)
	private String token;

	@Column(name = "USR_ACTIVE", unique = false, nullable = true)
	private Boolean isActive;
	
	@Column(name = "USR_ACTIVE_DESC", unique = false, nullable = true)
	private String reasonBLocked;

}
