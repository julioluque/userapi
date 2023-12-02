package ar.com.jluque.userapi.service;

import java.util.List;

import ar.com.jluque.userapi.entity.UserEntity;

public interface UserApiService {

	String echoTestService();

	List<UserEntity> getUsersWithPhones();
}
