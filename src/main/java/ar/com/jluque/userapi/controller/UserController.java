package ar.com.jluque.userapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.jluque.userapi.dto.UserDataDto;
import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/echotest")
	public ResponseEntity<String> echotest() {
		return new ResponseEntity<>(userService.echoTestService(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserResponseDto> addUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
	}

	@PutMapping("/{id}/bloquer")
	public ResponseEntity<UserResponseDto> blockUser(@PathVariable UUID id, @RequestBody UserDataDto userDataDto) {
		return new ResponseEntity<>(userService.bloquerUser(id, userDataDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
