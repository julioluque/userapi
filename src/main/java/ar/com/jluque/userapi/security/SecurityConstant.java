package ar.com.jluque.userapi.security;

public class SecurityConstant {

	private SecurityConstant() {
	}

	public static final long JWT_EXPIRATION_TOKEN = 300000; // 5 minutos
	public static final String JWT_FIRMA = "firma";
	
	public static final String[] MATCHER_AUTH = {"/auth/**"};
	public static final String[] MATCHER_H2 = {"/h2-console/**", "/favicon.ico/**"};
	public static final String[] MATCHER_SWAGGER = {"/swagger*/**", "/swagger-ui/**", "/v3/api-docs/**", "/configuration/**", "/webjars/**"};
}
