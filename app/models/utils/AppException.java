package models.utils;

/**
 * User: yesnault Date: 25/01/12
 */
public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppException() {
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(Throwable cause) {
		super(cause);
	}
}