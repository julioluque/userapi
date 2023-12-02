package ar.com.jluque.userapi.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReponseDto extends BaseDto {

	private static final long serialVersionUID = 6792054436219913259L;
	
	private String userName;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastLogin;
	private String token;
	private Boolean isActive;
}
