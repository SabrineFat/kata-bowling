package fr.kata.bowling.game.model;

import fr.kata.bowling.game.utils.GameUtils;

/**
 * The frame
 * 
 * @author Sabrine
 *
 */
public class Frame {


	private String firstRoll;
	private String secondRoll;

	private boolean hasBonus;
	private String nextThrow;

	public Frame() {
	}

	public Frame(String firstRoll, String secondRoll, boolean hasBonus) {
		this.firstRoll = firstRoll;
		this.secondRoll = secondRoll;
		this.hasBonus = hasBonus;
	}
	
	public boolean isStrike() {
		return GameUtils.STRIKE_INDICATE.equals(firstRoll);
	}

	public boolean isSpare() {
		return GameUtils.SPARE_INDICATE.equals(secondRoll);
	}

	// -----------------------------------------------------------------------------------------------------------------------
	public boolean isHasBonus() {
		return hasBonus;
	}

	public void setHasBonus(boolean hasBonus) {
		this.hasBonus = hasBonus;
	}

	public String getFirstRoll() {
		return firstRoll;
	}

	public void setFirstRoll(String firstRoll) {
		this.firstRoll = firstRoll;
	}

	public String getSecondRoll() {
		return secondRoll;
	}

	public void setSecondRoll(String secondRoll) {
		this.secondRoll = secondRoll;
	}

	public String getNextThrow() {
		return nextThrow;
	}

	public void setNextThrow(String nextThrow) {
		this.nextThrow = nextThrow;
	}

}
