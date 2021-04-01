package fr.kata.bowling.game.application;

import fr.kata.bowling.game.exception.BowlingException;
import fr.kata.bowling.game.model.Game;

/**
 * The BowlingGame interface
 * 
 * @author Sabrine
 *
 */
public interface BowlingGame {

	int calculateGameScore(Game game) throws BowlingException;

}
