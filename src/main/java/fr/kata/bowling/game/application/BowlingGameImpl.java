package fr.kata.bowling.game.application;

import java.util.List;

import fr.kata.bowling.game.exception.BowlingException;
import fr.kata.bowling.game.model.Frame;
import fr.kata.bowling.game.model.Game;

/**
 * The BowlingGameImpl
 * 
 * @author Sabrine
 *
 */
public class BowlingGameImpl implements BowlingGame{

	/**
	 * Calculate the final score of the game
	 * 
	 * @param game : the game that contains the score as a string input
	 * @return the final score
	 * @throws BowlingException Signals that an BowlingException has occurred.
	 */
	@Override
	public int calculateGameScore(Game game) throws BowlingException {

		FrameBuilder framesBuilder = new FrameBuilder();
		List<Frame> frames = framesBuilder.build(game);
		return calculateScoreListFrames(frames);

	}

	/**
	 * Calculate the total score for a list of frames
	 * 
	 * @param frames : list of frames
	 * @return the score
	 */
	private int calculateScoreListFrames(List<Frame> frames) {
		return frames.stream().mapToInt(frame -> calculateScoreFrame(frame)).sum();
	}

	/**
	 * Calculate the score for a single frame
	 * 
	 * @param frame : the frame
	 * @return the score
	 */
	private int calculateScoreFrame(Frame frame) {
		FrameGameImpl frameGame  = new FrameGameImpl();
		
		if (frame.isHasBonus()) {
			return 0;
		}
		if (frame.isSpare() || frame.isStrike()) {
			return frameGame.calculateScore(frame) + frameGame.calculateBonus(frame);
		}

		return frameGame.calculateScore(frame);
	}
}
