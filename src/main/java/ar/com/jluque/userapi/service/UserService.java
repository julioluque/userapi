package ar.com.jluque.userapi.service;

import java.util.List;
import java.util.UUID;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.dto.UserStatus;

public interface UserService {

	String echoTestService();

	UserResponseDto getUserById(UUID id);

	List<UserResponseDto> getAllUsers();

	UserResponseDto addUser(UserDto userDto);

	UserResponseDto updateUser(UUID id, UserDto userDto);

	UserResponseDto bloquerUser(UUID id, UserStatus userStatus);

	void deleteUser(UUID id);
}
