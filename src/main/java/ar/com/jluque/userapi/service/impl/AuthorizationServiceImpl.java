package ar.com.jluque.userapi.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.jluque.userapi.dto.AuthLoginDto;
import ar.com.jluque.userapi.dto.AuthRegistroDto;
import ar.com.jluque.userapi.dto.AuthResponseDto;
import ar.com.jluque.userapi.entity.AuthRoleEntity;
import ar.com.jluque.userapi.entity.AuthUserEntity;
import ar.com.jluque.userapi.exception.custom.FieldExistCustomException;
import ar.com.jluque.userapi.repository.RolesRepository;
import ar.com.jluque.userapi.repository.UsuarioRepository;
import ar.com.jluque.userapi.security.JwtGenerator;
import ar.com.jluque.userapi.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private RolesRepository rolesRepository;
	private UsuarioRepository usuarioRepository;
	private JwtGenerator jwtGenerator;

	@Autowired
	public AuthorizationServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			RolesRepository rolesRepository, UsuarioRepository usuarioRepository, JwtGenerator jwtGenerator) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.rolesRepository = rolesRepository;
		this.usuarioRepository = usuarioRepository;
		this.jwtGenerator = jwtGenerator;
	}

	public String register(AuthRegistroDto authRegistroDto) {

		if (usuarioRepository.existsByUsername(authRegistroDto.getUsername()))
			throw new FieldExistCustomException("El usuario ya existe.");
		
		AuthRoleEntity rol = rolesRepository.findByName(authRegistroDto.getRole()).get();

		AuthUserEntity authUserEntity = new AuthUserEntity();
		authUserEntity.setUsername(authRegistroDto.getUsername());
		authUserEntity.setPassword(passwordEncoder.encode(authRegistroDto.getPassword()));
		authUserEntity.setRoles(Collections.singletonList(rol));

		usuarioRepository.save(authUserEntity);

		return "Registro exitoso de nuevo usuario";
	}

	public AuthResponseDto login(AuthLoginDto authLoginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authLoginDto.getUsername(), authLoginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtGenerator.generarToken(authentication);

		AuthResponseDto authResponseDto = new AuthResponseDto();
		authResponseDto.setAccesToken(token);

		return authResponseDto;
	}
}
