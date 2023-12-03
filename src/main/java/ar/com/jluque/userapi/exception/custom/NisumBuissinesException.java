package ar.com.jluque.userapi.exception.custom;

public class NisumBuissinesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Nisum General Error";

	public NisumBuissinesException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
