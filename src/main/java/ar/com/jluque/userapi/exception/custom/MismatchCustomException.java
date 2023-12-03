package ar.com.jluque.userapi.exception.custom;

public class MismatchCustomException extends BadRequestCustomException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Mis Match";

	public MismatchCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
