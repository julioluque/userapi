package ar.com.jluque.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto {

	private String number;
	private String cityCode;
	private String countryCode;
//	"number": "1234567",
//	"citycode": "1",
//	"contrycode": "57"

}