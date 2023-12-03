package ar.com.jluque.userapi.utils;

import java.util.regex.Pattern;

public class UserApiConstant {

	private UserApiConstant() {
	}

	public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
	public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

	public static final String USER_STATUS_01 = "CREATED!";
	public static final String USER_STATUS_02 = "MODIFIED!";
	public static final String USER_STATUS_03 = "BLOQUED!";
	public static final String USER_STATUS_04 = "REACTIVATED!";
}
