package com.blogspot.nhu313.tictactoe;

import com.blogspot.nhu313.tictactoe.game.Game;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.player.Player;
import com.blogspot.nhu313.tictactoe.player.PlayerFactory;
import com.blogspot.nhu313.tictactoe.ui.BrownBlueGUI;

/**
 * This is a tic tac toe game. See <a href="http://en.wikipedia.org/wiki/Tic-tac-toe">wikipedia</a> 
 * for more information on the game. This game has a built in AI player. You can create the player 
 * by calling PlayerFactory.getComputerPlayer(value). You can also get a human player using the 
 * same factory. 
 * 
 * The game decides the next first player by the following:
 * - If player 1 won or the game end with player 1, regardless of who start, 
 *   player 2 will start the second game
 * - If player 1 start the game and the new game button is pressed (regardless of who pressed it),  
 *   then player 2 will start the new game. 
 *   
 * To start the game, run the class as a Java Application.
 * 
 * @author Nhu Nguyen
 *
 */
public class TicTacToe {

	public static void main(String[] args){
    	Game game = new Game(GameProperties.BOARD_SIZE);
		
    	Player computer = PlayerFactory.getComputerPlayer(TicTacToeValue.X);
		Player player = PlayerFactory.getHumanPlayer("You",TicTacToeValue.O);

		game.setPlayers(computer, player);
		
		game.start();

		BrownBlueGUI gui = new BrownBlueGUI();
		gui.createGUI(game);
		gui.displayGUI();
	}
}
