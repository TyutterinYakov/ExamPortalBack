package portal.api.exception;

public class InvalidFileException extends RuntimeException {

	public InvalidFileException() {
		super();
	}

	public InvalidFileException(String message) {
		super(message);
	}

	public InvalidFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
