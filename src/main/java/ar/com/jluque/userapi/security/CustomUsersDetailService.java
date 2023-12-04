package ar.com.jluque.userapi.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.jluque.userapi.entity.AuthRoleEntity;
import ar.com.jluque.userapi.entity.AuthUserEntity;
import ar.com.jluque.userapi.repository.UsuarioRepository;

@Service
public class CustomUsersDetailService implements UserDetailsService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public CustomUsersDetailService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Collection<GrantedAuthority> mapToAuthorities(List<AuthRoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUserEntity usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		return new User(usuario.getUsername(), usuario.getPassword(), mapToAuthorities(usuario.getRoles()));

	}

}
