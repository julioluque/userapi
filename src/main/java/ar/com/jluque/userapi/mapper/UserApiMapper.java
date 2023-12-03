package ar.com.jluque.userapi.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.PhoneEntity;
import ar.com.jluque.userapi.entity.UserEntity;

public class UserApiMapper {

	private UserApiMapper() {
	}

	public static UserEntity userMapperDtoToEntity(UserDto userDto) {

		List<PhoneEntity> phoneEntityList = new ArrayList<>();

		userDto.getPhones().forEach(p -> phoneEntityList.add(PhoneEntity.builder().number(p.getNumber())
				.cityCode(p.getCityCode()).countryCode(p.getCountryCode()).build()));

		UserEntity userEntity = UserEntity.builder().name(userDto.getName()).email(userDto.getEmail())
				.password(userDto.getPassword()).phones(phoneEntityList).build();

		phoneEntityList.forEach(phoneEntity -> phoneEntity.setUser(userEntity));

		return userEntity;
	}

	public static UserResponseDto responseDtoBuild(UserDto userDto, UserEntity userEntity) {
		LocalDateTime dateTime = LocalDateTime.now();
		return UserResponseDto.builder().id(userEntity.getId()).userName(userEntity.getName()).created(dateTime)
				.modified(dateTime).lastLogin(dateTime).token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1")
				.isActive(true).build();
	}
}
