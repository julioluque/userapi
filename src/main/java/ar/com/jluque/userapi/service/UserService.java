package ar.com.jluque.userapi.service;

import java.util.List;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;

public interface UserApiService {

	String echoTestService();

	UserEntity findById(Long id);

	List<UserEntity> getUsersWithPhones();

	UserResponseDto newUser(UserDto userDto);

}
