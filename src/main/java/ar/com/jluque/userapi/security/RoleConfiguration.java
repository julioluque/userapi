package ar.com.jluque.userapi.security;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ar.com.jluque.userapi.entity.AuthRoleEntity;
import ar.com.jluque.userapi.repository.RolesRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class RoleConfiguration {

	private RolesRepository rolesRepository;

	@Autowired
	public RoleConfiguration(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

	@PostConstruct
	public void settingRoles() {
		List<AuthRoleEntity> newRoles = Stream.of("ADMIN", "USER").map(name -> {
			AuthRoleEntity role = new AuthRoleEntity();
			role.setName(name);
			return role;
		}).toList();

		rolesRepository.saveAll(newRoles);
	}
}