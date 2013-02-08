package com.blogspot.nhu313.tictactoe.strategy;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

public class StrategyWinTest {
	private static final int BOARD_SIZE = 3;
	private StrategyWin strategy;	
	private final static TicTacToeValue PLAYER_VALUE = TicTacToeValue.X;
	private TicTacToeValue[] squareValues;
	private Board board;
	
	@Before
	public void setUp(){
		strategy = new StrategyWin(null);
		squareValues = new TicTacToeValue[BOARD_SIZE];
		board = new Board(BOARD_SIZE);
	}
	
	@After
	public void tearDown(){
		strategy = null;
		squareValues = null;
		board = null;
	}

	@Test
	public void testGetWinningPositionWithEmptySquares(){
		Assert.assertEquals(StrategyWin.INVALID_POSITION, strategy.getWinningPosition(PLAYER_VALUE, squareValues));
	}
	
	@Test
	public void testGetWinningPositionWithOnePlayerValue(){
		for (int i = 0; i < squareValues.length; i++){
			squareValues[i] = PLAYER_VALUE;
			Assert.assertEquals(StrategyWin.INVALID_POSITION, strategy.getWinningPosition(PLAYER_VALUE, squareValues));
			squareValues[i] = null;
		}
	}
	
	@Test
	public void testGetWinningPosition(){
		for (int i = 0; i < squareValues.length; i++){
			populateSquareValues(PLAYER_VALUE);
			squareValues[i] = null;
			Assert.assertEquals(i, strategy.getWinningPosition(PLAYER_VALUE, squareValues));
		}
	}	

	private void populateSquareValues(TicTacToeValue value) {
		for (int i = 0; i < squareValues.length; i++){
			squareValues[i] = value;
		}
	}
	
	@Test
	public void testGetWinningPositionWithOpponentAndPlayerValue(){
		squareValues[0] = getOpponentValue();
		squareValues[1] = PLAYER_VALUE;
		Assert.assertEquals(StrategyWin.INVALID_POSITION, strategy.getWinningPosition(PLAYER_VALUE, squareValues));
		squareValues[squareValues.length - 1] = getOpponentValue();
		squareValues[0] = PLAYER_VALUE;
		Assert.assertEquals(StrategyWin.INVALID_POSITION, strategy.getWinningPosition(PLAYER_VALUE, squareValues));
	}
	
	@Test
	public void testGetWinningPositionWithOnlyOneOpponentValue(){
		for (int i = 0; i < squareValues.length; i++){
			squareValues[i] = getOpponentValue();
			Assert.assertEquals(StrategyWin.INVALID_POSITION, strategy.getWinningPosition(PLAYER_VALUE, squareValues));
		}
	}
	
	@Test
	public void testGetWinningPositionWithOnlyOpponentValue(){
		for (int i = 0; i < squareValues.length; i++){
			populateSquareValues(getOpponentValue());
			squareValues[i] = null;
			Assert.assertEquals(StrategyWin.INVALID_POSITION, strategy.getWinningPosition(PLAYER_VALUE, squareValues));
		}
	}
	
	private TicTacToeValue getOpponentValue() {
		return (TicTacToeValue.X == PLAYER_VALUE) ? TicTacToeValue.O : TicTacToeValue.X;
	}
	
	@Test
	public void testGetLeftDiagonalWinPosition(){		
		for (int i = 0; i < board.size(); i++){
			populateLeftDiagonalBoardWithPlayerValue();
			Position expectedPosition = new Position(i,i);
			board.markSquare(null, expectedPosition);
			Assert.assertEquals(expectedPosition, strategy.getLeftDiagonalWinPosition(PLAYER_VALUE, board));
		}
	}
	
	@Test
	public void testGetLeftDiagonalWinPositionWithEmptyBoard(){
		Assert.assertNull(strategy.getLeftDiagonalWinPosition(PLAYER_VALUE, board));
	}

	private void populateLeftDiagonalBoardWithPlayerValue() {
		for (int i = 0; i < board.size(); i++){
			board.markSquare(PLAYER_VALUE,  new Position(i, i));
		}
	}
	
	@Test
	public void testGetRightDiagonalWinPosition(){
		int lastSquareIndex = board.size() - 1;
		for (int i = 0; i < board.size(); i++){
			populateRightDiagonalBoardWithPlayerValue();
			Position expectedPosition = new Position(i,lastSquareIndex - i);
			board.markSquare(null, expectedPosition);
			Assert.assertEquals(expectedPosition, strategy.getRightDiagonalWinPosition(PLAYER_VALUE, board));
		}
	}
	
	@Test
	public void testGetRightDiagonalWinPositionWithEmptyBoard(){
		Assert.assertNull(strategy.getRightDiagonalWinPosition(PLAYER_VALUE, board));
	}

	private void populateRightDiagonalBoardWithPlayerValue() {
		int lastSquareIndex = board.size() - 1;
		for (int i = 0; i < board.size(); i++){
			board.markSquare(PLAYER_VALUE,  new Position(i, lastSquareIndex -i));
		}		
	}
	
	@Test
	public void testGetHorizontalWinPosition(){
		for (int row = 0; row < board.size(); row++){
			populateRow(row, PLAYER_VALUE);
			Position expectedPosition = new Position(row, board.size() - 1);
			Assert.assertEquals(expectedPosition, strategy.getHorizontalWinPosition(PLAYER_VALUE, board));
			populateRow(row, null);
		}
	}

	private void populateRow(int row, TicTacToeValue playerValue) {
		for (int col = 0; col < board.size() - 1; col++){
			board.markSquare(playerValue,  new Position(row, col));
		}
	}
	
	@Test
	public void testGetHorizontalWinPositionWithEmptyBoard(){
		Assert.assertNull(strategy.getHorizontalWinPosition(PLAYER_VALUE, board));
	}
	
	@Test
	public void testGetVerticaltalWinPosition(){
		for (int col = 0; col < board.size(); col++){
			populateColumn(col, PLAYER_VALUE);
			Position expectedPosition = new Position(board.size() - 1, col);
			Assert.assertEquals(expectedPosition, strategy.getVerticalWinPosition(PLAYER_VALUE, board));
			populateColumn(col, null);
		}
	}

	private void populateColumn(int col, TicTacToeValue playerValue) {
		for (int row = 0; row < board.size() - 1; row++){
			board.markSquare(playerValue,  new Position(row, col));
		}
	}
	
	@Test
	public void testGetVerticaltalWinPositionWithEmptyBoard(){
		Assert.assertNull(strategy.getVerticalWinPosition(PLAYER_VALUE, board));
	}
}
