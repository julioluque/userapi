package ar.com.jluque.userapi.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public String generarToken(Authentication authentication) {
		Date tiempoActual = new Date();
		Date expiracionToken = new Date(tiempoActual.getTime() + SecurityConstant.JWT_EXPIRATION_TOKEN);

		return Jwts.builder()
				.setSubject(authentication.getName())
				.setIssuedAt(tiempoActual)
				.setExpiration(expiracionToken)
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_FIRMA)
				.compact();
	}
	
	public String obtenerUsernameDeJwt(String token) {
		Claims claim = Jwts.parser()
				.setSigningKey(SecurityConstant.JWT_FIRMA)
				.parseClaimsJws(token)
				.getBody();
		return claim.getSubject();
	}
	
	public Boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstant.JWT_FIRMA).parse(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("token Expirado o incorrecto");
		}
	}
	

}
