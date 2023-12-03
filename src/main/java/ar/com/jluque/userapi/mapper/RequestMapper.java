package ar.com.jluque.userapi.mapper;

import ar.com.jluque.userapi.dto.UserDto;
import ar.com.jluque.userapi.exception.custom.FieldExistCustomException;

public class RequestMapper {

	public RequestMapper() {
	}

	public static boolean paramsValid(UserDto userDto) throws FieldExistCustomException{

		// TODO validar regex
		if (userDto.getEmail().equals("ejemplo@example.com"))
			throw new FieldExistCustomException("Email existente.");

		return true;
	}

}
