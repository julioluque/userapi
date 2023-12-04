package ar.com.jluque.userapi.security;

public class SecurityConstant {

	private SecurityConstant() {
	}

	public static final long JWT_EXPIRATION_TOKEN = 300000; // 5 minutos
	
	public static final String JWT_FIRMA = "firma";
	
	public static final String REQUEST_MATCHER_ENDPOINT = "/auth/**";

}
