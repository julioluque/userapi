package ar.com.jluque.userapi.exception.custom;

public class ConflictCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Conflict";

	public ConflictCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
