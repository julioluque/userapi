package ar.com.jluque.userapi.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.jluque.userapi.controller.UserController;
import ar.com.jluque.userapi.dto.PhoneDto;
import ar.com.jluque.userapi.dto.UserDataDto;
import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.repository.UserRepository;
import ar.com.jluque.userapi.service.UserService;
import ar.com.jluque.userapi.utils.UserApiConstant;

public class UserControlerTest extends BaseIT {

	@InjectMocks
	private UserController controller;

	@Mock
	private UserService service;

	@Mock
	private UserRepository repository;

	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void echoTestDatabaseConection200Test() throws Exception {
		String response = "TestDatabase = true";
		when(service.echoTestService()).thenReturn(response);
		mockMvc.perform(get("/users/echotest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	void echoTestDatabaseNoConection200Test() throws Exception {
		String response = "TestDatabase = false";
		when(service.echoTestService()).thenReturn(response);
		mockMvc.perform(get("/users/echotest").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	void getAllUsers200Test() throws Exception {
		UUID uuidValue = UUID.randomUUID();

		UserDto userDto1 = UserDto.builder().name("julio luque 1").email("julio.luque1@example.com")
				.password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();

		UserDataDto userDataDto1 = UserDataDto.builder().created(LocalDateTime.now().minusDays(3L))
				.modified(LocalDateTime.now().minusHours(5L)).lastLogin(LocalDateTime.now().minusMinutes(10))
				.token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1").isActive(true)
				.status(UserApiConstant.USER_STATUS_02).build();

		UserResponseDto response1 = UserResponseDto.builder().id(uuidValue).userInfo(userDto1).userData(userDataDto1)
				.build();

		UserDto userDto2 = UserDto.builder().name("julio luque 2").email("julio.luque2@example.com")
				.password("Secreto2")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();

		UserDataDto userDataDto2 = UserDataDto.builder().created(LocalDateTime.now().minusDays(3L))
				.modified(LocalDateTime.now().minusHours(5L)).lastLogin(LocalDateTime.now().minusMinutes(10))
				.token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1").isActive(true)
				.status(UserApiConstant.USER_STATUS_02).build();

		UserResponseDto response2 = UserResponseDto.builder().id(uuidValue).userInfo(userDto2).userData(userDataDto2)
				.build();

		List<UserResponseDto> responseList = Arrays.asList(response1, response2);

		when(service.getAllUsers()).thenReturn(responseList);

		mockMvc.perform(
				get("/users").contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	void getUserById200Test() throws Exception {
		UUID uuidValue = UUID.randomUUID();

		PhoneDto phoneDto1 = PhoneDto.builder().number("12341" + (int) (Math.random() * 100000)).cityCode("CityA")
				.countryCode("CountryX").build();

		PhoneDto phoneDto2 = PhoneDto.builder().number("12341" + (int) (Math.random() * 100000)).cityCode("CityA")
				.countryCode("CountryX").build();

		UserDto userDto = UserDto.builder().name("julio luque 1").email("julio.luque1@example.com").password("Secreto1")
				.phones(Arrays.asList(phoneDto1, phoneDto2)).build();

		UserDataDto userDataDto = UserDataDto.builder().created(LocalDateTime.now().minusDays(3L))
				.modified(LocalDateTime.now().minusHours(5L)).lastLogin(LocalDateTime.now().minusMinutes(10))
				.token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1").isActive(true)
				.status(UserApiConstant.USER_STATUS_02).build();

		UserResponseDto response = UserResponseDto.builder().id(uuidValue).userInfo(userDto).userData(userDataDto)
				.build();

		when(service.getUserById(any())).thenReturn(response);

		mockMvc.perform(get("/users/{id}", uuidValue).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	void addUser201Test() throws Exception {
		UserDto userDtoRequest = UserDto.builder().name("julio luque 1").email("julio.luque1@example.com")
				.password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();

		String someToken = "Bearer sometoken.asdfplajsdfla";

		service.addUser(userDtoRequest, someToken);
		verify(service, times(1)).addUser(userDtoRequest, someToken);

		mockMvc.perform(post("/users", userDtoRequest).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userDtoRequest)).header(HttpHeaders.AUTHORIZATION, someToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	void updateUser200Test() throws Exception {
		UUID uuidValue = UUID.randomUUID();

		UserDto userDto = UserDto.builder().name("julio luque 1").email("julio.luque1@example.com").password("Secreto1")
				.phones(Arrays.asList(PhoneDto.builder().number("12341" + (int) (Math.random() * 100000))
						.cityCode("CityA").countryCode("CountryX").build()))
				.build();

		String someToken = "Bearer sometoken.asdfplajsdfla";

		service.updateUser(uuidValue, userDto, someToken);
		verify(service, times(1)).updateUser(uuidValue, userDto, someToken);

		mockMvc.perform(put("/users/{id}", uuidValue, userDto).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userDto)).header(HttpHeaders.AUTHORIZATION, someToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void blockUser200Test() throws Exception {
		UUID uuidValue = UUID.randomUUID();
		UserDataDto userDataDto = UserDataDto.builder().created(LocalDateTime.now().minusDays(3L))
				.modified(LocalDateTime.now().minusHours(5L)).lastLogin(LocalDateTime.now().minusMinutes(10))
				.token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1").isActive(true)
				.status(UserApiConstant.USER_STATUS_02).build();

		String someToken = "Bearer sometoken.asdfplajsdfla";

		service.bloquerUser(uuidValue, userDataDto, someToken);
		verify(service, times(1)).bloquerUser(uuidValue, userDataDto, someToken);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		mockMvc.perform(put("/users/{id}/bloquer", uuidValue, userDataDto).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userDataDto)).header(HttpHeaders.AUTHORIZATION, someToken)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void deleteUser200Test() throws Exception {
		UUID uuidValue = UUID.randomUUID();

		// Perform the deletion through mockMvc
		mockMvc.perform(delete("/users/{id}", uuidValue).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent());

		// Verify that the entity is deleted from the repository
		verify(service, times(1)).deleteUser(eq(uuidValue));
		assertThat(repository.findById(uuidValue)).isEmpty();
	}
}
