package com.blogspot.nhu313.tictactoe.strategy;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

public class StrategySecondTurnAsSecondPlayerTest {
	private final static TicTacToeValue COMPUTER_VALUE = TicTacToeValue.X;
	private static final TicTacToeValue OPPONENT_VALUE = TicTacToeValue.getOpponentValue(COMPUTER_VALUE);
	private StrategySecondTurnAsSecondPlayer strategy;
	private Board board;
	private List<Position> opponentPositions;

	@Before
	public void setUp(){
		strategy = new StrategySecondTurnAsSecondPlayer(null);
		board = new Board(3);
		opponentPositions = new LinkedList<Position>();
	}
	
	@After
	public void tearDown(){
		strategy = null;
		board = null;
		opponentPositions = null;
	}
	
	@Test
	public void testGetNextMoveFromChild_ThirdTurn(){
		board.markSquare(COMPUTER_VALUE, board.getCenterSquarePosition());
		board.markSquare(COMPUTER_VALUE, new Position(0,0));
		board.markSquare(COMPUTER_VALUE, new Position(0,1));
		board.markSquare(COMPUTER_VALUE, new Position(0,2));
		Assert.assertNull(strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	/**
	 *  [X][ ][ ]
		[ ][0][ ]
		[e][ ][0]
		
	 * e = expected position
	 */
	@Test
	public void testGetNextMoveFromChild_As2ndPlayerAndOpponentChoseOppositeBottomCorner(){
		Position expectedPosition = new Position(board.getEndCorner(), 0);
		
		Position playerMove = new Position(0,0);
		opponentPositions.add(board.getCenterSquarePosition());
		opponentPositions.add(new Position(board.getEndCorner(), board.getEndCorner()));
		
		markPositionsAsSecondPlayer(playerMove);
		
		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));		
	}

	@Test
	public void testGetNextMoveFromChild_As2ndPlayerOpponentChoseOppositeCornerForAllCorners(){
		Position[] cornerPositions = getCornerPositions();
		Position[] expectedPositions = getExpectedCornerPositions();
		
		for (int i = 0; i < cornerPositions.length; i++){
			board = new Board(board.size());
			
			Position expectedPosition = expectedPositions[i];
			
			opponentPositions.add(board.getCenterSquarePosition());
			opponentPositions.add(cornerPositions[i]);
			Position playerFirstMove = board.getOppositeCorner(opponentPositions.get(1));
			markPositionsAsSecondPlayer(playerFirstMove);
			Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
			opponentPositions.clear();
		}
		
	}

	private Position[] getExpectedCornerPositions() {
		Position[] cornerPositions = new Position[4];
		cornerPositions[0] = new Position(0,board.getEndCorner());
		cornerPositions[1] = new Position(0, 0);
		cornerPositions[2] = new Position(board.getEndCorner(), board.getEndCorner());
		cornerPositions[3] = new Position(board.getEndCorner(), 0);
		return cornerPositions;
	}
	
	private Position[] getCornerPositions() {
		Position[] cornerPositions = new Position[4];
		cornerPositions[0] = new Position(0,0);
		cornerPositions[1] = new Position(0, board.getEndCorner());
		cornerPositions[2] = new Position(board.getEndCorner(), 0);
		cornerPositions[3] = new Position(board.getEndCorner(), board.getEndCorner());
		return cornerPositions;
	}
	
	/**
	 *  [ ][ ][X]
		[e][0][ ]
		[X][ ][ ]
		
	 * e = expected position
	 */
	@Test
	public void testWhenOpponentUseOppositeCorners(){
		Position expectedPosition = new Position(board.getCenterSquarePosition().getRow(), 0);

		opponentPositions.add(new Position(0,board.getEndCorner()));
		opponentPositions.add(new Position(board.getEndCorner(), 0));

		markPositionsAsSecondPlayer(board.getCenterSquarePosition());

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	private void markPositionsAsSecondPlayer(Position playerPosition){
		board.markSquare(OPPONENT_VALUE, opponentPositions.get(0));		
		board.markSquare(COMPUTER_VALUE, playerPosition);
		board.markSquare(OPPONENT_VALUE, opponentPositions.get(1));		
	}
	
	/**
	 *  [ ][ ][ ]
		[0][X][ ]
		[e][0][ ]
		
		Opponent Pos1 = [2,1]
	 *  e = expected position
	 */
	@Test
	public void testWhenOpponentUseOppositeMiddleColumnMiddleAsFirstMove(){
		Position expectedPosition = new Position(board.getEndCorner(), 0);

		Position playerPosition = board.getCenterSquarePosition();
		opponentPositions.add(new Position(1, 0));
		opponentPositions.add(new Position(board.getEndCorner(), 1));
		
		markPositionsAsSecondPlayer(playerPosition);
		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	/**
	 *  [ ][ ][ ]
		[0][X][ ]
		[e][0][ ]
		
		Opponent Pos1 = [1,0]
	 *  e = expected position
	 */
	@Test
	public void testWhenOpponentUseFirstColumnMiddleAsFirstMove(){
		Position expectedPosition = new Position(board.getEndCorner(), 0);

		Position playerPosition = board.getCenterSquarePosition();
		opponentPositions.add(new Position(1, 0));
		opponentPositions.add(new Position(board.getEndCorner(), 1));
		
		markPositionsAsSecondPlayer(playerPosition);

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	/**
	 *  [ ][0][e]
		[ ][X][0]
		[ ][ ][ ]
	 * e = expected position
	 */
	@Test
	public void testWhenOpponentChooseMiddleTopRight(){
		Position expectedPosition = new Position(0, board.getEndCorner());

		Position playerPosition = board.getCenterSquarePosition();
		opponentPositions.add(new Position(0, 1));
		opponentPositions.add(new Position(1,board.getEndCorner()));
		
		markPositionsAsSecondPlayer(playerPosition);
		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	/**
	 *  [ ][ ][ ]
		[ ][X][0]
		[ ][0][e]
	 * e = expected position
	 */
	@Test
	public void testWhenOpponentChooseMiddleBottomRight(){
		Position expectedPosition = new Position(board.getEndCorner(), board.getEndCorner());

		Position playerPosition = board.getCenterSquarePosition();
		opponentPositions.add(new Position(1, board.getEndCorner()));
		opponentPositions.add(new Position(board.getEndCorner(), 1));
		
		markPositionsAsSecondPlayer(playerPosition);
		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	/**
	 *  [e][0][ ]
		[0][X][ ]
		[ ][ ][ ]
	 * e = expected position
	 */
	@Test
	public void testWhenOpponentChooseMiddleLeftTop(){
		Position expectedPosition = new Position(0,0);

		Position playerPosition = board.getCenterSquarePosition();
		opponentPositions.add(new Position(1, 0));
		opponentPositions.add(new Position(0, 1));
		
		markPositionsAsSecondPlayer(playerPosition);
		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
}