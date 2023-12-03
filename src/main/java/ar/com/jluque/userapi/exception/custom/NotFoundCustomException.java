package ar.com.jluque.userapi.exception.custom;

public class NotFoundCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Not Found";

	public NotFoundCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
