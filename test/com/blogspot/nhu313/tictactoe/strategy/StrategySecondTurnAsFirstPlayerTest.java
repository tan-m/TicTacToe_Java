package com.blogspot.nhu313.tictactoe.strategy;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

public class StrategySecondTurnAsFirstPlayerTest {
	private final static TicTacToeValue PLAYER_VALUE = TicTacToeValue.X;
	private static final TicTacToeValue OPPONENT_VALUE = TicTacToeValue.getOpponentValue(PLAYER_VALUE);
	private StrategySecondTurnAsFirstPlayer strategy;
	private Board board;

	@Before
	public void setUp(){
		strategy = new StrategySecondTurnAsFirstPlayer(null);
		board = new Board(3);
	}
	
	@After
	public void tearDown(){
		strategy = null;
		board = null;
	}
	
	@Test
	public void testGetNextMoveFromChild_FirstTurn(){
		board.markSquare(PLAYER_VALUE, new Position(0,0));
		Assert.assertNull(strategy.getNextMoveFromChild(PLAYER_VALUE, board));
	}
	
	@Test
	public void testGetNextMoveFromChild_AsFirstPlayerAndOpponentChoseCorner(){
		Position expectedPosition = new Position(0,0);
		Position opponentPosition = new Position(board.getEndCorner(), board.getEndCorner());
		testNextMoveFronChildAsFirstPlayer(expectedPosition, opponentPosition);
	}
	
	@Test
	public void testGetNextMoveFromChild_AsFirstPlayerAndOpponentChoseMiddleOfFirstColumn(){
		Position expectedPosition = new Position(0, board.getEndCorner());
		Position opponentPosition = new Position(board.getCenterSquarePosition().getRow(),0);
		testNextMoveFronChildAsFirstPlayer(expectedPosition, opponentPosition);
	}

	@Test
	public void testGetNextMoveFromChild_AsFirstPlayerAndOpponentChoseMiddleOfLastColumn(){
		Position expectedPosition = new Position(0, 0);
		Position opponentPosition = new Position(board.getCenterSquarePosition().getRow(),board.getEndCorner());
		testNextMoveFronChildAsFirstPlayer(expectedPosition, opponentPosition);
	}
	
	@Test
	public void testGetNextMoveFromChild_AsFirstPlayerAndOpponentChoseMiddleOfFirstRow(){
		Position expectedPosition = new Position(board.getEndCorner(), 0);
		Position opponentPosition = new Position(0, board.getCenterSquarePosition().getColumn());
		testNextMoveFronChildAsFirstPlayer(expectedPosition, opponentPosition);
	}
	
	@Test
	public void testGetNextMoveFromChild_AsFirstPlayerAndOpponentChoseMiddleOfLastRow(){
		Position expectedPosition = new Position(0, 0);
		Position opponentPosition = new Position(board.getEndCorner(), board.getCenterSquarePosition().getColumn());
		testNextMoveFronChildAsFirstPlayer(expectedPosition, opponentPosition);
	}
	
	private void testNextMoveFronChildAsFirstPlayer(Position expectedPosition, Position opponentPosition) {
		board.markSquare(PLAYER_VALUE, board.getCenterSquarePosition());
		board.markSquare(OPPONENT_VALUE,  opponentPosition);
		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(PLAYER_VALUE, board));
	}
}