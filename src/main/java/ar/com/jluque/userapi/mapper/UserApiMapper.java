package ar.com.jluque.userapi.mapper;

import java.util.ArrayList;
import java.util.List;

import ar.com.jluque.userapi.dto.UserRequestDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.PhoneEntity;
import ar.com.jluque.userapi.entity.UserEntity;

public class UserApiMapper {

	public UserApiMapper() {
	}

	public static UserEntity userMapperDtoToEntity(UserRequestDto request) {

		List<PhoneEntity> phoneEntityList = new ArrayList<>();

		request.getPhones().forEach(p -> phoneEntityList.add(PhoneEntity.builder().number(p.getNumber())
				.cityCode(p.getCityCode()).countryCode(p.getCountryCode()).build()));
//		for (PhoneDto p : request.getPhones()) {
//			phoneEntityList.add(PhoneEntity.builder().number(p.getNumber()).cityCode(p.getCityCode())
//					.countryCode(p.getCountryCode()).build());
//		}

		return UserEntity.builder().name(request.getName()).email(request.getEmail()).password("somepassword")
				.phones(phoneEntityList).build();
	}

	public static UserResponseDto responseDtoBuild(UserRequestDto userRequestDto, UserEntity newUserEntity) {
		return new UserResponseDto();
	}
}
