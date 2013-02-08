package com.blogspot.nhu313.tictactoe.strategy;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

/**
 * Note: e int the tic tac toe board mean that's the expected value
 */
public class StrategyThirdTurnTest {

	private final static TicTacToeValue COMPUTER_VALUE = TicTacToeValue.X;
	TicTacToeValue OPPONENT_VALUE = TicTacToeValue.getOpponentValue(COMPUTER_VALUE);

	private StrategyThirdTurn strategy;
	private Board board;

	@Before
	public void setUp(){
		strategy = new StrategyThirdTurn(null);
		board = new Board(3);
		board.markSquare(COMPUTER_VALUE, board.getCenterSquarePosition());
	}
	
	@After
	public void tearDown(){
		strategy = null;
		board = null;
	}
	
	@Test
	public void getNextMoveFromChild_NotFirstPlayer(){
		board.markSquare(OPPONENT_VALUE, new Position(0,0));		
		board.markSquare(COMPUTER_VALUE, new Position(board.getEndCorner(), board.getEndCorner()));
		board.markSquare(OPPONENT_VALUE, new Position(getBoardCenter(),board.getEndCorner()));
		Position opponentPosition = new Position(board.getEndCorner(), 0);
		board.markSquare(TicTacToeValue.getOpponentValue(COMPUTER_VALUE), opponentPosition);
		Assert.assertNull(strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	/**
	 *  [O][ ][ ]
		[ ][X][O]
		[e][ ][X]
	 */
	@Test
	public void getNextMoveFromChild1(){
		board.markSquare(OPPONENT_VALUE, new Position(0,0));		
		board.markSquare(COMPUTER_VALUE, new Position(board.getEndCorner(), board.getEndCorner()));
		board.markSquare(OPPONENT_VALUE, new Position(getBoardCenter(),board.getEndCorner()));
		
		Position expectedPosition = new Position(board.getEndCorner(), 0);
		
		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}

	/**
	 *  [O][ ][e]
		[ ][X][ ]
		[ ][0][X]
	 */
	@Test
	public void getNextMoveFromChild1a(){
		board.markSquare(OPPONENT_VALUE, new Position(0,0));		
		board.markSquare(COMPUTER_VALUE, new Position(board.getEndCorner(), board.getEndCorner()));
		board.markSquare(OPPONENT_VALUE, new Position(board.getEndCorner(), getBoardCenter()));
		
		Position expectedPosition = new Position(0, board.getEndCorner());

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	
	/**
	 *  [X][ ][e]
		[O][X][ ]
		[ ][ ][O]
	 */
	@Test
	public void getNextMoveFromChild2(){
		board.markSquare(OPPONENT_VALUE, new Position(board.getEndCorner(), board.getEndCorner()));
		board.markSquare(COMPUTER_VALUE, new Position(0,0));
		board.markSquare(OPPONENT_VALUE, new Position(getBoardCenter(),0));

		Position expectedPosition = new Position(0, board.getEndCorner());

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}
	
	/**
	 *  [X][0][ ]
		[ ][X][ ]
		[e][ ][O]
	 */
	@Test
	public void getNextMoveFromChild2a(){
		board.markSquare(OPPONENT_VALUE, new Position(board.getEndCorner(), board.getEndCorner()));
		board.markSquare(COMPUTER_VALUE, new Position(0,0));
		board.markSquare(OPPONENT_VALUE, new Position(0, getBoardCenter()));

		Position expectedPosition = new Position(board.getEndCorner(), 0);

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}

	/**
	 *  [e][ ][X]
		[ ][X][0]
		[0][ ][ ]
	 */
	@Test
	public void getNextMoveFromChild3(){
		board.markSquare(OPPONENT_VALUE, new Position(board.getEndCorner(), 0));		
		board.markSquare(COMPUTER_VALUE, new Position(0, board.getEndCorner()));
		board.markSquare(OPPONENT_VALUE, new Position(getBoardCenter(),board.getEndCorner()));
		
		Position expectedPosition = new Position(0, 0);

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}

	/**
	 *  [ ][0][X]
		[ ][X][ ]
		[0][ ][e]
	 */
	@Test
	public void getNextMoveFromChild3a(){
		board.markSquare(OPPONENT_VALUE, new Position(board.getEndCorner(),0));		
		board.markSquare(COMPUTER_VALUE, new Position(0, board.getEndCorner()));
		board.markSquare(OPPONENT_VALUE, new Position(0, getBoardCenter()));
		
		Position expectedPosition = new Position(board.getEndCorner(), board.getEndCorner());

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}

	/**
	 *  [e][ ][0]
		[ ][X][ ]
		[X][0][ ]
	 */
	@Test
	public void getNextMoveFromChild4(){
		board.markSquare(OPPONENT_VALUE, new Position(0, board.getEndCorner()));
		board.markSquare(COMPUTER_VALUE, new Position(board.getEndCorner(),0));
		board.markSquare(OPPONENT_VALUE, new Position(board.getEndCorner(), getBoardCenter()));

		Position expectedPosition = new Position(0, 0);

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}	

	/**
	 *  [ ][ ][0]
		[0][X][ ]
		[X][ ][e]
	 */
	@Test
	public void getNextMoveFromChild4a(){
		board.markSquare(OPPONENT_VALUE, new Position(0, board.getEndCorner()));
		board.markSquare(COMPUTER_VALUE, new Position(board.getEndCorner(),0));
		board.markSquare(OPPONENT_VALUE, new Position(getBoardCenter(),0));

		Position expectedPosition = new Position(board.getEndCorner(), board.getEndCorner());

		Assert.assertEquals(expectedPosition, strategy.getNextMoveFromChild(COMPUTER_VALUE, board));
	}

	private int getBoardCenter() {
		return board.size()/2;
	}
}
