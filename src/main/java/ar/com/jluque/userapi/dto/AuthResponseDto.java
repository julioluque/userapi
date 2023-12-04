package ar.com.jluque.userapi.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto implements Serializable {

	private static final long serialVersionUID = -6223943371552817464L;

	private String accesToken;
	private String tokenType = "Bearer ";

}
