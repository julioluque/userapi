package ar.com.jluque.userapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.exception.custom.FieldExistCustomException;
import ar.com.jluque.userapi.mapper.RequestMapper;
import ar.com.jluque.userapi.mapper.UserApiMapper;
import ar.com.jluque.userapi.repository.UserRepository;
import ar.com.jluque.userapi.service.UserApiService;
import jakarta.transaction.Transactional;

@Service
public class UserApiServiceImpl implements UserApiService {

	private final UserRepository repository;

	public UserApiServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public String echoTestService() {
		return "TestDatabase = " + repository.isConect();
	}

	@Override
	public UserEntity findById(Long id) {
		Optional<UserEntity> userEntity = repository.findById(id);
		return userEntity.get();
	}

	@Transactional
	public List<UserEntity> getUsersWithPhones() {
		return repository.findAll();
	}

	@Transactional
	public UserResponseDto newUser(UserDto userDto) {

		RequestMapper.paramsValid(userDto);

		Optional<UserEntity> userEntity = repository.findByEmail(userDto.getEmail());
		if (userEntity.isPresent()) {
			throw new FieldExistCustomException("El correo ya esta registrado.");
		}

		UserEntity newUserEntity = UserApiMapper.userMapperDtoToEntity(userDto);

		newUserEntity = repository.save(newUserEntity);

		return UserApiMapper.responseDtoBuild(userDto, newUserEntity);
	}

}
