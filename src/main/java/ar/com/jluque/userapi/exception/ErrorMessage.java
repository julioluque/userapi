package ar.com.jluque.userapi.exception;

import lombok.Data;

@Data
public class ErrorMessage {

	private String mensaje;

//	private String exception;
//	private String path;

//	public ErrorMessage(Exception exception, String path) {
//		this.exception = exception.getClass().getSimpleName();
//		this.message = exception.getMessage() + ", ON:" + path;
//		this.path = path;
//	}

	public ErrorMessage(Exception exception) {
		this.mensaje = exception.getMessage();
	}
}
