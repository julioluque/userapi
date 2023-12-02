package ar.com.jluque.userapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.jluque.userapi.entity.UserEntity;
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

	@Transactional
	public List<UserEntity> getUsersWithPhones() {
	    return repository.findAll();
	}
}
