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
public class AuthLoginDto implements Serializable {

	private static final long serialVersionUID = 8513105900753274917L;

	private String username;
	private String password;
}
