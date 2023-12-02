package ar.com.jluque.userapi.mapper;

import ar.com.jluque.userapi.dto.UserRequestDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;

public class UserApiMapper {

	public UserApiMapper() {
	}

	public static UserEntity userMapperDtoToEntity(UserRequestDto userRequestDto) {
		return new UserEntity();
	}

	public static UserResponseDto responseDtoBuild(UserRequestDto userRequestDto, UserEntity newUserEntity) {
		return new UserResponseDto();
	}
}
