package fr.kata.bowling.game.exception;

import fr.kata.bowling.game.utils.ErrorCode;

public class BowlingException extends Exception {

	private static final long serialVersionUID = 1203235600425182178L;

	private final ErrorCode code;

	public BowlingException() {
		super();
		code = null;
	}

	/**
	 * Constructor with message.
	 *
	 * @param message
	 *            .
	 */
	public BowlingException(String message) {
		super(message);
		code = null;
	}

	/**
	 * Constructor with the specified ErrorCode.
	 * 
	 * @param code
	 *            the error code.
	 */
	public BowlingException(final ErrorCode code) {
		super(code.getMsg(), null, true, false);
		this.code = code;
	}

	/**
	 * The code error.
	 *
	 * @return the code error.
	 */
	public ErrorCode getCode() {
		return code;
	}

}
