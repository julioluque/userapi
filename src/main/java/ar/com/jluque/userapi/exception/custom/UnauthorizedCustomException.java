package ar.com.jluque.userapi.exception.custom;

public class UnauthorizedCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Unauthorized";

	public UnauthorizedCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
