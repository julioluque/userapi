package ar.com.jluque.userapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.jluque.userapi.dto.AuthLoginDto;
import ar.com.jluque.userapi.dto.AuthRegistroDto;
import ar.com.jluque.userapi.dto.AuthResponseDto;
import ar.com.jluque.userapi.service.AuthorizationService;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

	private AuthorizationService authorizationService;

	@Autowired
	public AuthorizationController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody AuthRegistroDto authRegistroDto) {
		return new ResponseEntity<>(authorizationService.register(authRegistroDto), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody AuthLoginDto authLoginDto) {
		return new ResponseEntity<>(authorizationService.login(authLoginDto), HttpStatus.OK);
	}

}
