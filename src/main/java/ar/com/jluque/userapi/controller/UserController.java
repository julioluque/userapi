package ar.com.jluque.userapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.service.UserApiService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	private UserApiService service;

	public UserController(UserApiService service) {
		this.service = service;
	}

	@GetMapping("/echotest")
	public ResponseEntity<String> echotest() {
		return new ResponseEntity<>(service.echoTestService(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserEntity>> findAll() {
		return new ResponseEntity<>(service.getUsersWithPhones(), HttpStatus.OK);
	}

	@PostMapping("/addUser/")
	public ResponseEntity<UserResponseDto> findUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(service.newUser(userDto), HttpStatus.OK);
	}

}
