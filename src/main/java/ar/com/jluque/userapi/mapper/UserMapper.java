package ar.com.jluque.userapi.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.com.jluque.userapi.dto.UserData;
import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.PhoneEntity;
import ar.com.jluque.userapi.entity.UserEntity;

public class UserMapper {

	private UserMapper() {
	}

	public static UserEntity newUserMapperDtoToEntity(UserDto userDto) {

		List<PhoneEntity> phoneEntityList = new ArrayList<>();

		userDto.getPhones().forEach(p -> phoneEntityList.add(PhoneEntity.builder().number(p.getNumber())
				.cityCode(p.getCityCode()).countryCode(p.getCountryCode()).build()));

		UserEntity userEntity = UserEntity.builder().name(userDto.getName()).email(userDto.getEmail())
				.password(userDto.getPassword()).phones(phoneEntityList).created(LocalDateTime.now())
				.lastLogin(LocalDateTime.now()).token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1").isActive(true)
				.build();

		phoneEntityList.forEach(phoneEntity -> phoneEntity.setUser(userEntity));

		return userEntity;
	}

	public static UserResponseDto responseMapperBuildToDto(UserEntity userEntity, UserDto userDto) {
		return UserResponseDto.builder()
				.id(userEntity.getId())
				.userInfo(userDto)
				.userData(UserData.builder()
						.created(userEntity.getCreated()).modified(userEntity.getModified())
						.lastLogin(userEntity.getLastLogin()).token(userEntity.getToken())
						.isActive(userEntity.getIsActive()).build())
				.build();
	}

	public static UserEntity updateUserMapperToEntity(UserEntity userEntity, UserDto userDto) {
		userEntity.setName(userDto.getName());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setIsActive(false);
		userEntity.setModified(LocalDateTime.now());
		userEntity.setLastLogin(LocalDateTime.now());
		return userEntity;
	}

}
