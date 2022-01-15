package portal.exception;

public class UserFoundException extends Exception {

	public UserFoundException() {
		super("UserName is already exist from DB");
	}
	
	public UserFoundException(String msg) {
		super(msg);
	}
	
	
}
