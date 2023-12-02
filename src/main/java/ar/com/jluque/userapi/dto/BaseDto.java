package ar.com.jluque.userapi.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto implements Serializable {

	private static final long serialVersionUID = -8092474508184262147L;

	private Long id;
}
