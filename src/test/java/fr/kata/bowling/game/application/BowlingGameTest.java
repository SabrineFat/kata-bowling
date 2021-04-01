package fr.kata.bowling.game.application;

import org.junit.Before;
import org.junit.Test;

import fr.kata.bowling.game.application.BowlingGameImpl;
import fr.kata.bowling.game.exception.BowlingException;
import fr.kata.bowling.game.model.Game;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test the application with different test cases.
 * @author Sabrine
 *
 */
public class BowlingGameTest {

	private Game game;
	private BowlingGameImpl bowlingGame;

	@Before
	public void init() {
		game = new Game();
		bowlingGame = new BowlingGameImpl();
	}

	@Test
	public void test_first_example_all_strike() throws BowlingException {
		game.setInput("X X X X X X X X X X X X ");
		int score = bowlingGame.calculateGameScore(game);

		assertThat(score).isEqualTo(300);
	}

	@Test
	public void test_second_example() throws BowlingException {
		game.setInput("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-");
		int score = bowlingGame.calculateGameScore(game);

		assertThat(score).isEqualTo(90);
	}

	@Test
	public void test_third_example_all_spare() throws BowlingException {
		game.setInput("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 ");
		int score = bowlingGame.calculateGameScore(game);

		assertThat(score).isEqualTo(150);
	}

	@Test
	public void test_score_of_a_game_without_strike_or_spare() throws BowlingException {
		game.setInput("2237512645 1234 562112");
		int score = bowlingGame.calculateGameScore(game);

		assertThat(score).isEqualTo(64);
	}

	@Test
	public void test_combin_strike_numbers() throws BowlingException {
		game.setInput("XX34X5599663312269");
		int score = bowlingGame.calculateGameScore(game);
		assertThat(score).isEqualTo(124);

	}

	@Test
	public void test_combin_strike_spare_numbers() throws BowlingException {
		game.setInput("9/ 0/ X X 62 7/ 8/ X 90 X X 8");
		int score = bowlingGame.calculateGameScore(game);
		assertThat(score).isEqualTo(176);

	}

	@Test
	public void test_combin_strike_spare_missed_numbers() throws BowlingException {
		game.setInput("8- 7- 53 9/ 9/ X 8- 51 3/ 9-");
		int score = bowlingGame.calculateGameScore(game);
		assertThat(score).isEqualTo(122);

	}

	@Test
	public void test_combin_strike_spare_missed_numbers_with_bonus() throws BowlingException {
		game.setInput("8/ 9- 44 72 9- X X 8- 35 9/7");
		int score = bowlingGame.calculateGameScore(game);
		assertThat(score).isEqualTo(133);

	}

	@Test
	public void test_score_with_bonus() throws BowlingException {
		game.setInput("123456885566778899X32");
		int score = bowlingGame.calculateGameScore(game);
		assertThat(score).isEqualTo(122);

	}

	@Test
	public void test_score_with_bonus_strike() throws BowlingException {
		// "123456885566778899X"-->117
		game.setInput("123456885566778899XXX");
		int score = bowlingGame.calculateGameScore(game);
		assertThat(score).isEqualTo(137);
	}

	@Test(expected = BowlingException.class)
	public void test_score_with_empty_score() throws BowlingException {
		game.setInput(" ");
		bowlingGame.calculateGameScore(game);
	}

	@Test(expected = BowlingException.class)
	public void test_score_with_error_input_score() throws BowlingException {
		game.setInput("1234567891*Test*99");
		bowlingGame.calculateGameScore(game);
	}

	@Test(expected = BowlingException.class)
	public void test_all_strikes_with_error_nb_of_rolls() throws BowlingException {
		game.setInput("XXXX");
		bowlingGame.calculateGameScore(game);
	}

}
