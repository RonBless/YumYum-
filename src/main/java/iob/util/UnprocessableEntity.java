package iob.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntity extends RuntimeException {
	private static final long serialVersionUID = 4135341446057363927L;

	public UnprocessableEntity() {
	}

	public UnprocessableEntity(String message) {
		super(message);
	}

	public UnprocessableEntity(Throwable cause) {
		super(cause);
	}

	public UnprocessableEntity(String message, Throwable cause) {
		super(message, cause);
	}
	
}

