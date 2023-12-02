package ar.com.jluque.userapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.service.UserApiService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserApiService service;

	@GetMapping("/echotest")
	public ResponseEntity<String> echotest() {
		return new ResponseEntity<>(service.echoTestService(), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<UserEntity> getUsers() {
		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
	}

}
