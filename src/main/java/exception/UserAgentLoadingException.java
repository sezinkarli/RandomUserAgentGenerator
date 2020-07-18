package exception;

public class UserAgentLoadingException extends RuntimeException {

	public UserAgentLoadingException(String message) {
		super(message);
	}

	public UserAgentLoadingException(String message, Throwable cause) {
		super(message, cause);
	}
}
