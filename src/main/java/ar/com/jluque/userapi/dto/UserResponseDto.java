package ar.com.jluque.userapi.dto;

import java.io.Serializable;
import java.util.UUID;

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
public class UserResponseDto implements Serializable {

	private static final long serialVersionUID = 6792054436219913259L;

	private UUID id;
	private UserDto userInfo;
	private UserData userData;

}
