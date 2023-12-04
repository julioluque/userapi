package ar.com.jluque.userapi.service;

import ar.com.jluque.userapi.dto.AuthLoginDto;
import ar.com.jluque.userapi.dto.AuthRegistroDto;
import ar.com.jluque.userapi.dto.AuthResponseDto;

public interface AuthorizationService {

	public String register(AuthRegistroDto authRegistroDto);

	public AuthResponseDto login(AuthLoginDto authLoginDto);
}
