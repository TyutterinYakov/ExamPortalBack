package portal.api.exception;

public class NotPermissionException extends RuntimeException {
	public NotPermissionException() {
		super();
	}
	
	public NotPermissionException(String msg) {
		super(msg);
	}

}
