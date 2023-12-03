package ar.com.jluque.userapi.exception.custom;

public class FieldExistCustomException extends ConflictCustomException {
	
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Field Exist";

	public FieldExistCustomException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
