package ar.com.jluque.userapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.jluque.userapi.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

	@Query(value = "SELECT 1=1", nativeQuery = true)
	boolean isConect();

	boolean existsByEmail(String email);

}
