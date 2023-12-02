package ar.com.jluque.userapi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private String name;
	private String email;
	private String password;
	private List<PhoneDto> phones;
	
//	"name": "Juan Rodriguez",
//	"email": "juan@rodriguez.org",
//	"password": "hunter2",
//	"phones": [

}
