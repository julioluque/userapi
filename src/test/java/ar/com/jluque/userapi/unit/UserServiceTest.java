package ar.com.jluque.userapi.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.jluque.userapi.dto.PhoneDto;
import ar.com.jluque.userapi.dto.UserDataDto;
import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.entity.PhoneEntity;
import ar.com.jluque.userapi.entity.UserEntity;
import ar.com.jluque.userapi.repository.UserRepository;
import ar.com.jluque.userapi.service.impl.UserServiceImpl;
import ar.com.jluque.userapi.utils.UserApiConstant;

public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllUsers200Test() throws Exception {

		UserEntity userEntity1 = UserEntity.builder().id(UUID.randomUUID()).name("pepe")
				.email("julio.luque1@example.com")
				.phones(Arrays.asList(PhoneEntity.builder().number("123456789").build())).build();

		UserEntity userEntity2 = UserEntity.builder().id(UUID.randomUUID()).name("luis")
				.email("julio.luque2@example.com")
				.phones(Arrays.asList(PhoneEntity.builder().number("123456701").build())).build();

		when(repository.findAll()).thenReturn(Arrays.asList(userEntity1, userEntity2));

		String emailExpected1 = "julio.luque1@example.com";
		String emailExpected2 = "julio.luque2@example.com";
		List<UserResponseDto> callServiceList = service.getAllUsers();

		assertEquals(callServiceList.get(0).getUserInfo().getEmail(), emailExpected1);
		assertEquals(callServiceList.get(1).getUserInfo().getEmail(), emailExpected2);
	}

	@Test
	void getUserById200Test() throws Exception {
		UUID uuidValue = UUID.randomUUID();

		UserEntity userEntity = UserEntity.builder().id(uuidValue).name("pepe").email("julio.luque1@example.com")
				.phones(Arrays.asList(PhoneEntity.builder().number("123456789").build())).build();

		when(repository.findById(any())).thenReturn(Optional.ofNullable(userEntity));

		String emailExpected = "julio.luque1@example.com";
		UserResponseDto callService = service.getUserById(uuidValue);
		assertEquals(callService.getUserInfo().getEmail(), emailExpected);
	}

}