package fr.kata.bowling.game.application;

import fr.kata.bowling.game.model.Frame;

/**
 * The FrameGame interface
 * 
 * @author Sabrine
 *
 */
public interface FrameGame {

	
	int calculateBonus(Frame frame);
	
	int calculateScore(Frame frame);
}
