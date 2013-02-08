package com.blogspot.nhu313.tictactoe.game;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

public class BoardTest {
	
	private Board board;
	
	@Before
	public void setUp(){
		board = new Board(3);
	}
	
	@After
	public void tearDown(){
		board = null;
	}

	@Test
	public void testGetSquareValues_NoValue(){
		Position position = new Position(0,0);
		Assert.assertNull(board.getSquareValue(position));
	}
	
	@Test
	public void testGetSquareValues(){
		Position position = board.getCenterSquarePosition();
		TicTacToeValue value = TicTacToeValue.O;
		board.markSquare(value, position);
		
		Assert.assertEquals(value, board.getSquareValue(position));
	}
	
	@Test
	public void testMarkSquare_CheckMoveHistory(){
		Position position = board.getCenterSquarePosition();
		board.markSquare(null, position);
		
		List<Position> moveHistory = board.getMoveHistory();
		Assert.assertEquals(1, moveHistory.size());
		Assert.assertTrue(moveHistory.contains(position));
	}
	
	@Test
	public void testMarkSquare_WithValue(){
		Position position = new Position(1,0);
		TicTacToeValue value = TicTacToeValue.O;
		board.markSquare(value , position);
		Assert.assertEquals(value, board.getSquareValue(position));
	}
	
	@Test
	public void testGetColumnValues(){
		TicTacToeValue value = TicTacToeValue.O;
		int col = board.size() - 1;
		board.markSquare(value, new Position(1,col));
		board.markSquare(value, new Position(2,col));

		TicTacToeValue[] expected = new TicTacToeValue[board.size()];
		expected[1] = value;
		expected[2] = value;
		
		Assert.assertArrayEquals(expected, board.getColumnValues(col));
	}
	
	@Test
	public void testGetLeftDiagonal(){
		TicTacToeValue value = TicTacToeValue.X;
		board.markSquare(value, new Position(0,0));
		board.markSquare(value, board.getCenterSquarePosition());
		board.markSquare(value, new Position(2,2));
		
		TicTacToeValue[] expected = getCompleteArray(value);
		
		Assert.assertArrayEquals(expected, board.getLeftDiagonalValues());
		
	}
	
	@Test
	public void testGetRightDiagonal(){
		TicTacToeValue value = TicTacToeValue.X;
		board.markSquare(value, new Position(0,2));
		board.markSquare(value, board.getCenterSquarePosition());
		board.markSquare(value, new Position(2,0));
		
		TicTacToeValue[] expected = getCompleteArray(value);
		
		Assert.assertArrayEquals(expected, board.getRightDiagonalValues());
	}

	private TicTacToeValue[] getCompleteArray(TicTacToeValue value) {
		TicTacToeValue[] expected = new TicTacToeValue[board.size()];
		expected[0] = value;
		expected[1] = value;
		expected[2] = value;
		return expected;
	}
	
	@Test
	public void testIsCornerPosition(){
		int endCorner = board.getEndCorner();
		Assert.assertTrue(board.isCornerPosition(new Position(0,0)));
		Assert.assertTrue(board.isCornerPosition(new Position(0,endCorner)));
		Assert.assertTrue(board.isCornerPosition(new Position(endCorner,0)));
		Assert.assertTrue(board.isCornerPosition(new Position(endCorner, endCorner)));
	}
	
	@Test
	public void testGetEndCorner(){
		Assert.assertEquals(2, board.getEndCorner());
	}
	
	@Test
	public void testGetOppositeCorner(){
		int endCorner = board.getEndCorner();
		Position topLeft = new Position(0,0);
		Position bottomLeft = new Position(endCorner, 0);
		Position topRight = new Position(0, endCorner);
		Position bottomRight =new Position(endCorner, endCorner);
		
		Assert.assertEquals(topLeft, board.getOppositeCorner(bottomRight));
		Assert.assertEquals(topRight, board.getOppositeCorner(bottomLeft));
		Assert.assertEquals(bottomLeft, board.getOppositeCorner(topRight));
		Assert.assertEquals(bottomRight, board.getOppositeCorner(topLeft));
	}
	
	@Test
	public void testCleanBoard(){
		TicTacToeValue value = TicTacToeValue.X;
		Position position = board.getCenterSquarePosition();
		board.markSquare(value , position);
		Assert.assertEquals(value, board.getSquareValue(position));
		Assert.assertEquals(1, board.getNumberOfMove());
		
		board.cleanBoard();
		Assert.assertEquals(null, board.getSquareValue(position));
		Assert.assertEquals(0, board.getMoveHistory().size());
		
	}
}
