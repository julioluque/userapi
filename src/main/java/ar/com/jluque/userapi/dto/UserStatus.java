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
public class UserStatus implements Serializable {

	private static final long serialVersionUID = -7533119533669758540L;

	private Boolean bloqued;

//	private Boolean status;

	private String reason;

}
