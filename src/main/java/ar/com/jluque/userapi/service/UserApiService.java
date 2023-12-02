package ar.com.jluque.userapi.service;

import ar.com.jluque.userapi.entity.UserEntity;

public interface UserApiService {

	public String echoTestService();

	public UserEntity getUsers();
}
