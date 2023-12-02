package ar.com.jluque.userapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.mapper.RequestMapper;
import ar.com.jluque.userapi.mapper.UserApiMapper;
import ar.com.jluque.userapi.repository.UserRepository;
import ar.com.jluque.userapi.service.UserApiService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
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

	@Override
	public UserResponseDto newUser(UserDto userDto) {

		// TODO: validar request
		if (RequestMapper.paramsValid(userDto)) {
			log.info("Param valid");
		}

		// TODO: consultar db para validar que no exista
		Optional<UserEntity> userEntity = repository.findByEmail(userDto.getEmail());
		if (userEntity.isPresent()) {
			log.error("lanzar error");
		}

		// TODO: Manejar errores

		// TODO: mapear nueo usuario, dto a entity
		UserEntity newUserEntity = UserApiMapper.userMapperDtoToEntity(userDto);

		// TODO: Persisitir nuevo usuario
		repository.save(newUserEntity);

		// TODO: mapear response
		return UserApiMapper.responseDtoBuild(userDto, newUserEntity);
	}

}
