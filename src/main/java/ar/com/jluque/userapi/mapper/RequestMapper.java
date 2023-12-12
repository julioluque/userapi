package ar.com.jluque.userapi.mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.exception.custom.ConflictCustomException;
import ar.com.jluque.userapi.utils.UserApiConstant;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RequestMapper {


	@Autowired
	private RequestMapper() {
	}

	public static void paramsValid(UserDto userDto) {
		if (!emailValidation(userDto.getEmail())) {
			log.error("Formato de email incorrecto.");
			throw new ConflictCustomException("Formato de email incorrecto.");
		}

//		if (!passwordValidation(userDto.getPassword())) {
//			log.error("Formato de password incorrecto.");
//			throw new ConflictCustomException("Formato de password incorrecto.");
//		}
	}

	public static boolean emailValidation(String email) {
		Matcher matcher = UserApiConstant.EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

//	public static boolean passwordValidation(String password) {
//		Matcher matcher = UserApiConstant.PASSWORD_PATTERN.matcher(password);
//		return matcher.matches();
//	}

}
