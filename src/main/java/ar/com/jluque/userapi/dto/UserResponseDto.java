package ar.com.jluque.userapi.dto;

import java.time.LocalDateTime;

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
public class UserResponseDto { // extends BaseDto {

	private static final long serialVersionUID = 6792054436219913259L;

	private Long id;
	private String userName;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastLogin;
	private String token;
	private Boolean isActive;

//	public static UserResponseDtoBuilder builder() {
//		return new UserResponseDtoBuilder();
//	}
}
