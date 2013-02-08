package com.blogspot.nhu313.tictactoe.game;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.Game;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.player.Player;
import com.blogspot.nhu313.tictactoe.player.PlayerFactory;
import com.blogspot.nhu313.tictactoe.position.Position;

public class GameTest {

	private Game game;
	Player player1;
	Player player2;

	@Before
	public void setUp(){
		game = new Game(3);
		player1 = PlayerFactory.getHumanPlayer("o", TicTacToeValue.X);
		player2 = PlayerFactory.getComputerPlayer(TicTacToeValue.O);
		game.setPlayers(player1, player2);
	}
	
	@After
	public void tearDown(){
		game = null;
	}

	@Test
	public void testPlayerChangeWhenComputerIsFirstPlayer(){
		game.setPlayers(player2, player1);
		Assert.assertEquals(player2, game.getCurrentPlayer());
		game.start();
		Assert.assertEquals(player1, game.getCurrentPlayer());
	}
	
	@Test
	public void testPlayerChangeWhenHumanIsFirstPlayer(){
		Assert.assertEquals(player1, game.getCurrentPlayer());
		game.start();
		Assert.assertEquals(player1, game.getCurrentPlayer());
	}

	@Test
	public void testPlayerChangeForHumans(){
		player2 = PlayerFactory.getHumanPlayer("o", TicTacToeValue.O);
		game.setPlayers(player1, player2);
		
		game.start();
		Assert.assertEquals(player1, game.getCurrentPlayer());

		game.processSquareClick();
		Assert.assertEquals(player2, game.getCurrentPlayer());
		
		markBoardForCurrentPlayerToWin();
		game.processSquareClick();
		Assert.assertEquals(player2, game.getCurrentPlayer());
		
		game.restart();
		Assert.assertEquals(player1, game.getCurrentPlayer());;
	}	
	
	@Test
	public void testPlayerChangeForHumanAndComputer(){
		game.setPlayers(player1, player2);
		game.start();
		Assert.assertEquals(player1, game.getCurrentPlayer());

		game.processSquareClick();
		//Player didn't move, but the game moved the computer player, so there 
		//should be one move on the board and the game is back to player1
		Assert.assertEquals(1, game.getBoard().getNumberOfMove());
		Assert.assertEquals(player1, game.getCurrentPlayer());
		
		markBoardForCurrentPlayerToWin();
		game.processSquareClick();
		Assert.assertEquals(player1, game.getCurrentPlayer());
		
		game.restart();
		//It's the computer move first. 
		Assert.assertEquals(1, game.getBoard().getNumberOfMove());		
		Assert.assertEquals(player1, game.getCurrentPlayer());
	}

	private void markBoardForCurrentPlayerToWin() {
		Board board = game.getBoard();
		for (int i = 0; i < board.size(); i++){
			board.markSquare(game.getCurrentPlayer().getValue(), new Position(0,i));
		}
	}

	@Test
	public void testChangePlayerForTie(){
		player1 = PlayerFactory.getComputerPlayer(TicTacToeValue.X);
		
		game.setPlayers(player1, player2);
		Assert.assertEquals(player1, game.getCurrentPlayer());

		game.start();
		Assert.assertFalse(game.isGameInProgress());
		Assert.assertTrue(game.isATie());
		Assert.assertEquals(player1, game.getCurrentPlayer());
		
		game.restart();
		Assert.assertEquals(player2, game.getCurrentPlayer());
	}
	
	@Test
	public void testIsGameInProgress(){
		game.setPlayers(player1, player2);
		Assert.assertFalse(game.isGameInProgress());
		
		game.start();
		Assert.assertTrue(game.isGameInProgress());
		
		markBoardForCurrentPlayerToWin();
		game.processSquareClick();
		Assert.assertFalse(game.isGameInProgress());
	}
	
	@Test
	public void testRestartPlayerChange(){
		player2 = PlayerFactory.getHumanPlayer("o", TicTacToeValue.O);

		game.setPlayers(player1, player2);
		game.start();	
		Assert.assertEquals(player1, game.getCurrentPlayer());
		
		game.restart();
		Assert.assertEquals(player2, game.getCurrentPlayer());
		
		game.processSquareClick();
		Assert.assertEquals(player1, game.getCurrentPlayer());
		
		game.restart();
		Assert.assertEquals(player1, game.getCurrentPlayer());
	}
}
