package com.blogspot.nhu313.tictactoe.strategy;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

public class StrategyFirstTurnTest {
	private static final TicTacToeValue PLAYER_VALUE = TicTacToeValue.X;
	private Board board;
	private Strategy strategy;
	
	@Before
	public void setUp(){
		board = new Board(3);
		strategy = new StrategyFirstTurn(null);
	}

	@After
	public void tearDown() throws Exception {
		board = null;
		strategy = null;
	}

	@Test
	public void getNextMoveFromChild_BoardIsEmpty() {
		Position position = strategy.getNextMoveFromChild(PLAYER_VALUE, board);
		Position expectedPosition = board.getCenterSquarePosition();
		Assert.assertEquals(expectedPosition, position);
	}
	
	@Test
	public void getNextMoveFromChild_FirstMoveNotCenter(){
		board.markSquare(PLAYER_VALUE, new Position(0,0));
		Position position = strategy.getNextMoveFromChild(PLAYER_VALUE, board);
		Position expectedPosition = board.getCenterSquarePosition();
		Assert.assertEquals(expectedPosition, position);
		
	}
	
	@Test
	public void getNextMoveFromChild_FirstMoveIsCenter(){
		board.markSquare(PLAYER_VALUE, board.getCenterSquarePosition());
		Position position = strategy.getNextMoveFromChild(PLAYER_VALUE, board);
		Assert.assertTrue(isAtCorner(position.getRow()));
		Assert.assertTrue(isAtCorner(position.getColumn()));		
	}

	private boolean isAtCorner(int number) {
		return number == 0 || number == board.size() - 1;
	}

	
	@Test
	public void testGetNextMoveWhenRandomNumberIsNot0(){
		int randomNumber = 1;
		int lastSquareIndex = board.size() - 1;

		testGetNextMoveWithMockedRandomNumber(randomNumber, lastSquareIndex);
	}

	@Test
	public void testGetNextMoveWhenRandomNumberIs0(){
		int randomNumber = 0;
		int firstSquareIndex = 0;
		testGetNextMoveWithMockedRandomNumber(randomNumber, firstSquareIndex);
	}

	private void testGetNextMoveWithMockedRandomNumber(int randomNumber, int expectedIndex) {
		StrategyFirstTurn mockedStrategy = EasyMock.createMockBuilder(StrategyFirstTurn.class).addMockedMethod("getRandomNumber").createMock();
		EasyMock.expect(mockedStrategy.getRandomNumber()).andReturn(randomNumber).times(2);
		EasyMock.replay(mockedStrategy);
		Position expectedPosition = new Position(expectedIndex, expectedIndex);
		Assert.assertEquals(expectedPosition , mockedStrategy.getRandomSquareCorner(board.size()));		
		EasyMock.verify(mockedStrategy);
	}
	
	
}
