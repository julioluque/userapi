package ar.com.jluque.userapi.service;

import java.util.List;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;

public interface UserService {

	String echoTestService();

	UserEntity getUserById(Long id);

	List<UserEntity> getAllUsers();

	UserResponseDto addUser(UserDto userDto);

	UserResponseDto updateUser(Long id, UserDto userDto);

	void deleteUser(Long id);
}
