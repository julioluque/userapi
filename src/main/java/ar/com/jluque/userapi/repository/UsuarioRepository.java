package ar.com.jluque.userapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.jluque.userapi.entity.AuthUserEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<AuthUserEntity, Long> {

	Optional<AuthUserEntity> findByUsername(String name);

	Boolean existsByUsername(String name);

}
