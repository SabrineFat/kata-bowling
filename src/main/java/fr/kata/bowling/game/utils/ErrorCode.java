package fr.kata.bowling.game.utils;

/**
 * Enum with different error code
 * @author Sabrine
 *
 */
public enum ErrorCode {


	ERROR_NB_FRAME("Le nombre de frames ne doit pas être inférieur à 10."),

	ERROR_INPUT_INCORRECT("Score incorrect.");

	private String msg;

	/**
	 * Constructor with message.
	 *
	 * @param msg
	 *            .
	 */
	private ErrorCode(final String msg) {
		this.msg = msg;
	}

	/**
	 * The msg.
	 *
	 * @return the msg.
	 */
	public String getMsg() {
		return msg;
	}

}
