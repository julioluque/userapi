package ar.com.jluque.userapi.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.com.jluque.userapi.dto.PhoneDto;
import ar.com.jluque.userapi.dto.UserDataDto;
import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.PhoneEntity;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.exception.custom.UnauthorizedCustomException;
import ar.com.jluque.userapi.utils.UserApiConstant;

public class UserMapper {

	private UserMapper() {
	}

	public static UserEntity newUserMapperDtoToEntity(UserDto userDto, String authorizationHeader) {

		String bearerToken = "";
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
			bearerToken = authorizationHeader.substring(7);
		else
			throw new UnauthorizedCustomException("Bearer Token Error");

		List<PhoneEntity> phoneEntityList = new ArrayList<>();

		userDto.getPhones().forEach(p -> phoneEntityList.add(PhoneEntity.builder().number(p.getNumber())
				.cityCode(p.getCityCode()).countryCode(p.getCountryCode()).build()));

		UserEntity userEntity = UserEntity.builder().name(userDto.getName()).email(userDto.getEmail())
				.password(userDto.getPassword()).phones(phoneEntityList).created(LocalDateTime.now())
				.lastLogin(LocalDateTime.now()).token(bearerToken).isActive(true).status(UserApiConstant.USER_STATUS_01)
				.build();

		phoneEntityList.forEach(phoneEntity -> phoneEntity.setUser(userEntity));

		return userEntity;
	}

	public static UserResponseDto responseMapperBuildToDto(UserEntity userEntity, UserDto userDto) {

		maskedPass(userDto);
		
		return UserResponseDto.builder().id(userEntity.getId()).userInfo(userDto)
				.userData(UserDataDto.builder().created(userEntity.getCreated()).modified(userEntity.getModified())
						.lastLogin(userEntity.getLastLogin()).token(userEntity.getToken())
						.isActive(userEntity.getIsActive()).status(userEntity.getStatus()).build())
				.build();
	}

	public static void maskedPass(UserDto userDto) {
		userDto.setPassword("*******");
	}

	public static UserEntity updateUserMapperToEntity(UserEntity userEntity, UserDto userDto,
			String authorizationHeader) {

		String bearerToken = "";
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
			bearerToken = authorizationHeader.substring(7);
		else
			throw new UnauthorizedCustomException("Bearer Token Error");

		userEntity.setName(userDto.getName());
		userEntity.setPassword(bearerToken);
		userEntity.setModified(LocalDateTime.now());
		userEntity.setLastLogin(LocalDateTime.now());
		userEntity.setStatus(UserApiConstant.USER_STATUS_02);
		return userEntity;
	}

	public static UserDto userMapperEntityToDto(UserEntity userEntity) {
		List<PhoneDto> phoneDtoList = new ArrayList<>();

		userEntity.getPhones().forEach(p -> phoneDtoList.add(PhoneDto.builder().number(p.getNumber())
				.cityCode(p.getCityCode()).countryCode(p.getCountryCode()).build()));

		return UserDto.builder().name(userEntity.getName()).email(userEntity.getEmail())
				.password(userEntity.getPassword()).phones(phoneDtoList).build();
	}

	public static UserEntity bloquerUserMapperToEntity(UserEntity userEntity, UserDataDto userDataDto) {
		userEntity.setIsActive(userDataDto.getIsActive());
		userEntity.setStatus(Boolean.FALSE.equals(userDataDto.getIsActive()) ? UserApiConstant.USER_STATUS_03
				: UserApiConstant.USER_STATUS_04);
		userEntity.setModified(LocalDateTime.now());
		userEntity.setLastLogin(LocalDateTime.now());
		return userEntity;
	}

}
