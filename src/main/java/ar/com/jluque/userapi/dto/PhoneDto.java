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
public class PhoneDto implements Serializable {

	private static final long serialVersionUID = 1540354800380206159L;

	private String number;
	private String cityCode;
	private String countryCode;

}
