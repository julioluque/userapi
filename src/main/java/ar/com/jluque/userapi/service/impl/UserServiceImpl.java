package ar.com.jluque.userapi.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.exception.custom.FieldExistCustomException;
import ar.com.jluque.userapi.exception.custom.NotFoundCustomException;
import ar.com.jluque.userapi.mapper.RequestMapper;
import ar.com.jluque.userapi.mapper.UserMapper;
import ar.com.jluque.userapi.repository.UserRepository;
import ar.com.jluque.userapi.service.UserService;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public String echoTestService() {
		return "TestDatabase = " + repository.isConect();
	}

	@Transactional
	public List<UserEntity> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public UserEntity getUserById(UUID id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundCustomException("No se encontro el usuario: " + id));
	}

	@Transactional
	public UserResponseDto addUser(UserDto userDto) {
		RequestMapper.paramsValid(userDto);

		if (repository.existsByEmail(userDto.getEmail()))
			throw new FieldExistCustomException("El correo ya está registrado.");

		UserEntity newUserEntity = UserMapper.newUserMapperDtoToEntity(userDto);
		newUserEntity = repository.save(newUserEntity);
		return UserMapper.responseMapperBuildToDto(newUserEntity, userDto);
	}

	@Transactional
	public UserResponseDto updateUser(UUID id, UserDto userDto) {
		UserEntity userEntity = getUserById(id);
		UserEntity updatedUserEntity = UserMapper.updateUserMapperToEntity(userEntity, userDto);
		updatedUserEntity = repository.save(updatedUserEntity);
		return UserMapper.responseMapperBuildToDto(updatedUserEntity, userDto);
	}

	@Transactional
	public void deleteUser(UUID id) {
		UserEntity userEntity = getUserById(id);
		repository.delete(userEntity);
	}
}
