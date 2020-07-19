package exception;

/**
 * Runtime exception for all user agent loading exceptions.
 * User agents are loaded from a text file.
 * */
public class UserAgentLoadingException extends RuntimeException {

	public UserAgentLoadingException(String message) {
		super(message);
	}

	public UserAgentLoadingException(String message, Throwable cause) {
		super(message, cause);
	}
}
