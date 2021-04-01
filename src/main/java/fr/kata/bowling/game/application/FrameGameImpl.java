package fr.kata.bowling.game.application;

import org.apache.commons.lang3.StringUtils;

import fr.kata.bowling.game.model.Frame;
import fr.kata.bowling.game.utils.GameUtils;

/**
 * The FrameGameImpl
 * 
 * @author Sabrine
 *
 */
public class FrameGameImpl implements FrameGame {


	/**
	 * Calculate the bonus for a frame
	 * 
	 * @param frame : the frame
	 * @return the bonus
	 *
	 */
	@Override
	public int calculateBonus(Frame frame) {
		int totalBonus = 0;

		if (frame != null && !StringUtils.isEmpty(frame.getNextThrow()) ) {
			String[] bonuses = frame.getNextThrow().split(GameUtils.EMPTY);

			for (String bonus : bonuses) {
				switch (bonus) {
				case GameUtils.STRIKE_INDICATE:
					totalBonus += 10;
					break;
				case GameUtils.SPARE_INDICATE:
					return 10;
				case GameUtils.MISS_INDICATE:
					break;
				default:
					totalBonus += Integer.parseInt(bonus);
				}
			}
		}
		return totalBonus;
	}

	/**
	 * Calculate the score
	 * 
	 * @param frame : the frame
	 * @return the score for a frame
	 *
	 */
	@Override
	public int calculateScore(Frame frame) {
		
		if(frame!=null){
			return frame.isSpare() || frame.isStrike() ? 10 : getFirstRollScore(frame) + getSecondRollScore(frame);
		}
		
		return 0;
	}


	// -----------------------------------------------------------------------------------------------------------------------
		private int getFirstRollScore(Frame frame) {
			return GameUtils.EMPTY.equals(frame.getFirstRoll()) 
					|| GameUtils.MISS_INDICATE.equals(frame.getFirstRoll()) ? 0
					: Integer.parseInt(frame.getFirstRoll());
		}
		
		private int getSecondRollScore(Frame frame) {
			return GameUtils.EMPTY.equals(frame.getSecondRoll()) 
					|| GameUtils.MISS_INDICATE.equals(frame.getSecondRoll()) ? 0
					: Integer.parseInt(frame.getSecondRoll());
		}
}
