package com.blogspot.nhu313.tictactoe.tools;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.GameProperties;
import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.player.Player;
import com.blogspot.nhu313.tictactoe.player.PlayerFactory;
import com.blogspot.nhu313.tictactoe.position.Position;

public class WinCalculatorTest {
	private final static int BOARD_SIZE = GameProperties.BOARD_SIZE;
	
	WinCalculator calculator;
	Board board;
	Player player;
	
	@Before
	public void setUp(){
		board = new Board(BOARD_SIZE);
		calculator = new WinCalculator(board);
		player = PlayerFactory.getHumanPlayer("Player 1", TicTacToeValue.X);
	}
	
	@After
	public void tearDown(){
		calculator = null;
		board = null;
		player = null;
	}
	
	@Test
	public void testWhenBoardIsEmpty() {
		Assert.assertFalse(calculator.hasPlayerWon(player));
	}
	
	@Test
	public void testWhenPlayerWinRow1(){
		final int row = 0;
		setHorizontalSquareToPlayerValue(player, row);
		Assert.assertTrue(calculator.hasPlayerWon(player));
	}
	
	@Test
	public void testWhenPlayerWinRow3(){
		final int row = 2;
		setHorizontalSquareToPlayerValue(player, row);
		Assert.assertTrue(calculator.hasPlayerWon(player));
	}

	@Test
	public void testWhenPlayerDidNotWinHorizontally(){
		final int row = 1;
		setHorizontalSquareToPlayerValue(player, row);
		Assert.assertTrue(calculator.hasPlayerWon(player));

		final int col = 2;
		board.markSquare(null, new Position(row, col));
		Assert.assertFalse(calculator.hasPlayerWon(player));
	}
	
	@Test
	public void testWhenPlayerWinCol1(){
		final int col = 0;
		setVertialSquareToPlayerValue(player, col);
		Assert.assertTrue(calculator.hasPlayerWon(player));
	}
	
	@Test
	public void testWhenPlayerWinCol3(){
		final int col = 2;
		setVertialSquareToPlayerValue(player, col);
		Assert.assertTrue(calculator.hasPlayerWon(player));
	}

	@Test
	public void testWhenPlayerDidNotWinVertically(){
		final int col = 1;
		setVertialSquareToPlayerValue(player, col);
		Assert.assertTrue(calculator.hasPlayerWon(player));

		final int row = 2;
		board.markSquare(null,  new Position(row, col));
		Assert.assertFalse(calculator.hasPlayerWon(player));
	}	
	
	private void setHorizontalSquareToPlayerValue(Player player, int row) {
		for (int i = 0; i < BOARD_SIZE; i++){
			board.markSquare(player.getValue(),  new Position(row, i));
		}
	}
	
	private void setVertialSquareToPlayerValue(Player player, int col) {
		for (int i = 0; i < BOARD_SIZE; i++){
			board.markSquare(player.getValue(),  new Position(i, col));
		}
	}
	
	@Test
	public void testWhenPlayerWinLeftDiagonal(){
		for (int i = 0; i < BOARD_SIZE; i++){
			board.markSquare(player.getValue(),  new Position(i, i));
		}
		Assert.assertTrue(calculator.hasPlayerWon(player));
	}
	
	@Test
	public void testWhenPlayerWinRightDiagonal(){
		for (int i = 0; i < BOARD_SIZE; i++){
			board.markSquare(player.getValue(),  new Position(i, 2 - i));
		}
		Assert.assertTrue(calculator.hasPlayerWon(player));
	}
	
	@Test
	public void testWhenPlayerDidNotWinLeftDiagonal(){
		for (int i = 0; i < BOARD_SIZE - 1; i++){
			board.markSquare(player.getValue(),  new Position(i, i));
		}
		
		Assert.assertFalse(calculator.hasPlayerWon(player));
	}
	
	@Test
	public void testWhenPlayerDidNotWinRightDiagonal(){
		for (int i = 0; i < BOARD_SIZE - 1; i++){
			board.markSquare(player.getValue(),  new Position(i, 2 - i));
		}
		Assert.assertFalse(calculator.hasPlayerWon(player));
	}
}
