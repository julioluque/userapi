package ar.com.jluque.userapi.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.jluque.userapi.controller.UserController;
import ar.com.jluque.userapi.dto.PhoneDto;
import ar.com.jluque.userapi.dto.UserDataDto;
import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.dto.UserResponseDto;
import ar.com.jluque.userapi.service.UserService;
import ar.com.jluque.userapi.utils.UserApiConstant;

public class UserControlerTest {

	@InjectMocks
	private UserController controller;

	@Mock
	private UserService service;

	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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

		UserResponseDto response = UserResponseDto.builder().id(uuidValue).userInfo(userDto).userData(userDataDto).build();

		when(service.getUserById(any())).thenReturn(response);

		mockMvc.perform(get("/users/{id}", uuidValue)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	}

}
