package portal.exception;

public class InvalidDataException extends RuntimeException {
	public InvalidDataException() {
		super();
	}
	
	public InvalidDataException(String msg) {
		super(msg);
	}
}
