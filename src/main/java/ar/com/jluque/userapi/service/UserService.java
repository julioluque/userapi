package ar.com.jluque.userapi.service;

import java.util.List;
import java.util.UUID;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;

public interface UserService {

	String echoTestService();

	UserEntity getUserById(UUID id);

	List<UserEntity> getAllUsers();

	UserResponseDto addUser(UserDto userDto);

	UserResponseDto updateUser(UUID id, UserDto userDto);

	void deleteUser(UUID id);
}
