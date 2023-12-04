package ar.com.jluque.userapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.jluque.userapi.entity.AuthRoleEntity;

@Repository
public interface RolesRepository extends JpaRepository<AuthRoleEntity, Long> {

	Optional<AuthRoleEntity> findByName(String name);

}
