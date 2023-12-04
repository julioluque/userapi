package ar.com.jluque.userapi.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = -1042496481767928226L;

	private String name;
	private String email;
	private String password;
	private List<PhoneDto> phones;

}
