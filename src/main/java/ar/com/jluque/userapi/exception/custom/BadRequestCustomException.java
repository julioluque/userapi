package ar.com.jluque.userapi.exception.custom;

public class BadRequestCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Bad Request";

	public BadRequestCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
