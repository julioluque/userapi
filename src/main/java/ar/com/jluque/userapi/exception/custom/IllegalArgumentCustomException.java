package ar.com.jluque.userapi.exception.custom;

public class IllegalArgumentCustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Illegal Argument";

	public IllegalArgumentCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
