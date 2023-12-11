package ar.com.jluque.userapi.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import ar.com.jluque.userapi.exception.custom.ConflictCustomException;
import ar.com.jluque.userapi.exception.custom.FieldExistCustomException;
import ar.com.jluque.userapi.exception.custom.NotFoundCustomException;
import ar.com.jluque.userapi.mapper.RequestMapper;
import ar.com.jluque.userapi.mapper.UserMapper;
import ar.com.jluque.userapi.repository.UserRepository;
import ar.com.jluque.userapi.service.impl.UserServiceImpl;
import ar.com.jluque.userapi.utils.UserApiConstant;

public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private RequestMapper mapper;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllUsersSuccessTest() throws Exception {

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
	void getAllUsersNotFoundTest() throws Exception {
		when(repository.findAll()).thenReturn(new ArrayList<UserEntity>());
		assertThrows(NotFoundCustomException.class, () -> service.getAllUsers());
	}

	@Test
	void getUserByIdSuccessTest() throws Exception {
		UUID uuidValue = UUID.randomUUID();

		UserEntity userEntity = UserEntity.builder().id(uuidValue).name("pepe").email("julio.luque1@example.com")
				.phones(Arrays.asList(PhoneEntity.builder().number("123456789").build())).build();

		when(repository.findById(any())).thenReturn(Optional.ofNullable(userEntity));

		String emailExpected = "julio.luque1@example.com";
		UserResponseDto callService = service.getUserById(uuidValue);
		assertEquals(callService.getUserInfo().getEmail(), emailExpected);
	}

	@Test
	void addUserFieldExistByEmailTest() throws Exception {
		when(repository.existsByEmail(anyString())).thenReturn(true);
		UserDto userDto = UserDto.builder().name("julio luque 1").email("julio@example.com").password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();
		String someToken = "sometoken.asdfasdiupqojdvqp";
		assertThrows(FieldExistCustomException.class, () -> service.addUser(userDto, someToken));
	}

	@Test
	void addUserEmailFormatExceptionTest() throws Exception {
		String emailIncorrecto = "formatoIncorrectoexample.com";
		UserDto userDto = UserDto.builder().email(emailIncorrecto)
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000)).build()))
				.build();
		String someToken = "sometoken";
		assertThrows(ConflictCustomException.class, () -> service.addUser(userDto, someToken));
	}

	@Test
	void addUserSuccessTest() throws Exception {
		when(repository.existsByEmail(anyString())).thenReturn(false);

		UserDto userDtoRequest = UserDto.builder().name("julio luque 1").email("julio@example.com").password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();
		String someTokenRequest = "Bearer sometoken.asdfasdiupqojdvqp";
		
		UserEntity newUserEntity = UserMapper.newUserMapperDtoToEntity(userDtoRequest, someTokenRequest);

		when(repository.save(any())).thenReturn(newUserEntity);

		UserDto userDto = UserDto.builder().name("julio luque 1").email("julio@example.com").password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();
		String someToken = "Bearer sometoken.asdfasdiupqojdvqp";

		UserResponseDto callToAddUser = service.addUser(userDto, someToken);
		String emailExpected = "julio@example.com";
		
		assertEquals(callToAddUser.getUserInfo().getEmail(), emailExpected);

	}

	@Test
	void updateUserNotFoundExceptionTest() throws Exception {
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));

		UUID uuidValue = UUID.randomUUID();
		UserDto userDto = UserDto.builder().email("julio@example.com")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000)).build()))
				.build();
		String someToken = "sometoken";
		
		assertThrows(NotFoundCustomException.class, () -> service.updateUser(uuidValue, userDto, someToken));
	}

	@Test
	void updateUserSuccessTest() throws Exception {
		UUID uuidValue = UUID.randomUUID();

		UserEntity userEntity = UserEntity.builder().id(uuidValue).name("pepe").email("julio.luque1@example.com")
				.phones(Arrays.asList(PhoneEntity.builder().number("123456789").build())).build();

		when(repository.findById(any())).thenReturn(Optional.ofNullable(userEntity));

		UserDto userDtoRequest = UserDto.builder().name("julio luque 1").email("julio@example.com").password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();
		String someTokenRequest = "Bearer sometoken.asdfasdiupqojdvqp";

		UserEntity newUserEntity = UserMapper.updateUserMapperToEntity(userEntity, userDtoRequest, someTokenRequest);

		when(repository.save(any())).thenReturn(newUserEntity);

		UserDto userDto = UserDto.builder().name("julio luque 1").email("julio@example.com").password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();
		String someToken = "Bearer sometoken.asdfasdiupqojdvqp";

		UserResponseDto callToAddUser = service.updateUser(uuidValue, userDto, someToken);
		String emailExpected = "julio@example.com";

		assertEquals(callToAddUser.getUserInfo().getEmail(), emailExpected);
	}

	@Test
	void bloquerUserNotFoundExceptionTest() throws Exception {
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));

		UUID uuidValue = UUID.randomUUID();
		UserDataDto userDataDto = UserDataDto.builder().created(LocalDateTime.now().minusDays(3L))
				.modified(LocalDateTime.now().minusHours(5L)).lastLogin(LocalDateTime.now().minusMinutes(10))
				.token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1").isActive(true)
				.status(UserApiConstant.USER_STATUS_02).build();
		String someToken = "Bearer sometoken";
		
		assertThrows(NotFoundCustomException.class, () -> service.bloquerUser(uuidValue, userDataDto, someToken));
	}

}