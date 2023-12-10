package ar.com.jluque.userapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
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
import ar.com.jluque.userapi.service.UserService;
import ar.com.jluque.userapi.utils.UserApiConstant;

class UserControllerTest {


	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getUserById200Test() throws Exception {
		UserResponseDto userResponseDto = mockUserResponseDto();
		when(userService.getUserById(any())).thenReturn(userResponseDto);
		UUID idMock = UUID_VALUE;
		UserResponseDto userByIdCall = userController.getUserById(idMock).getBody();
		String emailExpected = "julio.luque1@example.com";
		assertEquals(userByIdCall.getUserInfo().getEmail(), emailExpected);
	}

	
	
	
	
	
	UUID UUID_VALUE = UUID.randomUUID();
	
	 public UserResponseDto mockUserResponseDto() {
	        return UserResponseDto.builder()
			        .id(UUID_VALUE)
			        .userInfo(mockUserDto())
			        .userData(mockUserDataDto())
			        .build();
	    }

	    public static UserDto mockUserDto() {
	        return UserDto.builder()
			        .name("julio luque 1")
			        .email("julio.luque1@example.com")
			        .password("Secreto1")
			        .phones(Arrays.asList(mockPhoneDto(), mockPhoneDto(), mockPhoneDto()))
			        .build();
	    }
	    
	    public static PhoneDto mockPhoneDto() {
	        return PhoneDto.builder()
					.number("12341" + String.valueOf(Math.random() * 1000))
			        .cityCode("CityA")
			        .countryCode("CountryX")
			        .build();
	    }

	    public static UserDataDto mockUserDataDto() {
	        return UserDataDto.builder()
			        .created(LocalDateTime.now().minusDays(3L))
			        .modified(LocalDateTime.now().minusHours(5L))
			        .lastLogin(LocalDateTime.now().minusMinutes(10))
			        .token("sometoken.a1v651qq546464a6s666DF65WD1q516fqwf1")
			        .isActive(true)
			        .status(UserApiConstant.USER_STATUS_02)
			        .build();
	    }
	
}
