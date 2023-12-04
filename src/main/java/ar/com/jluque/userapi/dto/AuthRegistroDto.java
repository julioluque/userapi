package ar.com.jluque.userapi.dto;

import java.io.Serializable;

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
public class AuthRegistroDto implements Serializable {

	private static final long serialVersionUID = -1771613799684478825L;

	private String username;
	private String password;
	private String role;

}
