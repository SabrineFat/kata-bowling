package fr.kata.bowling.game.application;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import fr.kata.bowling.game.exception.BowlingException;
import fr.kata.bowling.game.model.Frame;
import fr.kata.bowling.game.model.Game;
import fr.kata.bowling.game.utils.ErrorCode;
import fr.kata.bowling.game.utils.GameUtils;

/**
 * The builder of the frame
 * 
 * @author Sabrine
 *
 */
public class FrameBuilder {

	/**
	 * return a list of frames from the game
	 * 
	 * @param game : the game with the score as input
	 * @return a list of frame
	 * @throws BowlingException Signals that an BowlingException has occurred.
	 */
	public List<Frame> build(Game game) throws BowlingException {
		String input = game.getInput();
		List<Frame> frames = new ArrayList<>();

		// vérifier que le score n'est pas vide et contient des valeurs valides
		verifyScoreInput(input);

		long countStrike = input.chars().filter(car -> car == GameUtils.STRIKE_INDICATE.charAt(0)).count();
		int nbMinimumOfFrame = 20 - ((int) countStrike * 2);

		input = input.replaceAll("\\s+", GameUtils.EMPTY);
		String[] records = input.split(GameUtils.EMPTY);

		if (records != null && records.length < nbMinimumOfFrame) {
			throw new BowlingException(ErrorCode.ERROR_NB_FRAME);
		}

		int maxLength = (records.length) - 1 + (int) countStrike;

		int index = 0;
		for (; index < maxLength; index++) {
			// On sort du boucle dés qu'on a 10 frames
			if (frames.size() == 10) {
				break;
			}
			frames.add(buildFrame(records, index));
			if (!isStrike(records[index])) {
				index++;
			}
		}
		if (isFrameBonusAuthorized(records.length, index, frames)) {
			frames.add(buildFrameWithBonus(records, index));
		}

		return frames;
	}

	/**
	 * 
	 * @param records
	 *            records
	 * @param index
	 *            the index in the list of frames
	 * @return a frame
	 */
	private Frame buildFrameWithBonus(String[] records, int index) {

		String secondRoll = GameUtils.EMPTY;

		if (records.length > index + 1) {
			secondRoll = records[index + 1];
		}

		return new Frame(records[index], secondRoll, true);

	}

	private Frame buildFrame(String[] records, int index) {
		Frame frame = new Frame();

		frame.setFirstRoll(records[index]);
		frame.setHasBonus(false);

		if (!frame.isStrike() && records.length > index + 1) {
			frame.setSecondRoll(records[index + 1]);
		}
		if (records.length > index + 2) {
			if (frame.isSpare()) {
				frame.setNextThrow(records[index + 2]);
			} else if (frame.isStrike()) {
				frame.setNextThrow(records[index + 1] + records[index + 2]);
			}
		}
		return frame;
	}

	private boolean isStrike(String record) {
		return GameUtils.STRIKE_INDICATE.equals(record);
	}

	private boolean isFrameBonusAuthorized(int lengthRecords, int index, List<Frame> frames) {
		return (lengthRecords > index && frames.size() == 10 && (frames.get(9).isStrike() || frames.get(9).isSpare()));
	}

	private void verifyScoreInput(String input) throws BowlingException {

		if (StringUtils.isEmpty(input)) {
			throw new BowlingException(ErrorCode.ERROR_INPUT_INCORRECT);
		}
		Pattern pattern = Pattern.compile("[0-9X/-]+");
		final Matcher matcher = pattern.matcher(input);

		if (!matcher.find()) {
			throw new BowlingException(ErrorCode.ERROR_INPUT_INCORRECT);
		}
	}

}
