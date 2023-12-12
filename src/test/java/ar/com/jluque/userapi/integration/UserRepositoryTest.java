package ar.com.jluque.userapi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.com.jluque.userapi.entity.PhoneEntity;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.repository.UserRepository;
import jakarta.persistence.EntityManager;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findByIdTest() {
		UUID uuidValue = UUID.randomUUID();
		UserEntity userEntity = UserEntity.builder().id(uuidValue).name("pepe").email("julio.luque1@example.com")
				.phones(Arrays.asList(PhoneEntity.builder().id(UUID.randomUUID()).number("123456789")
						.cityCode("someCity").countryCode("someCountry").build()))
				.build();

		List<UserEntity> userEntityList = new ArrayList<>();
		userEntityList.add(userEntity);

		entityManager.persist(userEntity);
		entityManager.flush();

		assertEquals(userRepository.findById(uuidValue), userEntityList.get(0));
	}

}
