package ar.com.jluque.userapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {

	private static String passwordRegex;

	@Value("${password.regex}")
	public void setPasswordRegex(String regex) {
		passwordRegex = regex;
	}

	public static String getPasswordRegex() {
		return passwordRegex;
	}
}