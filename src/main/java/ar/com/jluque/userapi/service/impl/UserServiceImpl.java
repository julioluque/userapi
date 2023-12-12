package ar.com.jluque.userapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ar.com.jluque.userapi.dto.UserDataDto;
import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.exception.custom.FieldExistCustomException;
import ar.com.jluque.userapi.exception.custom.NisumBuissinesException;
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
	public List<UserResponseDto> getAllUsers() {
		List<UserEntity> userEntityList = repository.findAll();

		if (userEntityList.isEmpty())
			throw new NotFoundCustomException("No se encontraron usuarios registrados.");

		List<UserResponseDto> responseList = new ArrayList<>();
		for (UserEntity userEntity : userEntityList) {
			UserDto userDto = UserMapper.userMapperEntityToDto(userEntity);
			UserMapper.maskedPass(userDto);
			UserResponseDto userResponseDto = UserMapper.responseMapperBuildToDto(userEntity, userDto);
			responseList.add(userResponseDto);
		}

		return responseList;
	}

	@Override
	public UserResponseDto getUserById(UUID id) {
		UserEntity userEntity = repository.findById(id)
				.orElseThrow(() -> new NotFoundCustomException("No se encontro el usuario: " + id));
		UserDto userDto = UserMapper.userMapperEntityToDto(userEntity);
		UserMapper.maskedPass(userDto);
		return UserMapper.responseMapperBuildToDto(userEntity, userDto);
	}

	@Transactional
	public UserResponseDto addUser(UserDto userDto, String authorizationHeader) {

		RequestMapper.paramsValid(userDto);

		if (repository.existsByEmail(userDto.getEmail()))
			throw new FieldExistCustomException("El correo ya está registrado.");

		UserEntity newUserEntity = UserMapper.newUserMapperDtoToEntity(userDto, authorizationHeader);
		newUserEntity = repository.save(newUserEntity);
		return UserMapper.responseMapperBuildToDto(newUserEntity, userDto);
	}

	@Transactional
	public UserResponseDto updateUser(UUID id, UserDto userDto, String authorizationHeader) {
		UserEntity userEntity = repository.findById(id)
				.orElseThrow(() -> new NotFoundCustomException("No se encontro el usuario: " + id));

		UserEntity updatedUserEntity = UserMapper.updateUserMapperToEntity(userEntity, userDto, authorizationHeader);
		updatedUserEntity = repository.save(updatedUserEntity);
		return UserMapper.responseMapperBuildToDto(updatedUserEntity, userDto);
	}

	@Transactional
	public UserResponseDto bloquerUser(UUID id, UserDataDto userDataDto, String authorizationHeader) {
		UserEntity userEntity = repository.findById(id)
				.orElseThrow(() -> new NotFoundCustomException("No se encontro el usuario: " + id));

		if (Objects.equals(userEntity.getIsActive(), userDataDto.getIsActive()))
			throw new NisumBuissinesException("Si cambios. El estado del usuario es el mismo que la peticion.");

		UserDto userDto = UserMapper.userMapperEntityToDto(userEntity);
		UserEntity updatedUserEntity = UserMapper.bloquerUserMapperToEntity(userEntity, userDataDto);
		updatedUserEntity = repository.save(updatedUserEntity);
		return UserMapper.responseMapperBuildToDto(updatedUserEntity, userDto);
	}

	@Transactional
	public void deleteUser(UUID id) {
		UserEntity userEntity = repository.findById(id)
				.orElseThrow(() -> new NotFoundCustomException("No se encontro el usuario: " + id));
		repository.delete(userEntity);
	}
}
