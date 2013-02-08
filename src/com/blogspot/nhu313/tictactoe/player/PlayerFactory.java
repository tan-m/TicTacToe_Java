package com.blogspot.nhu313.tictactoe.player;

import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.strategy.StrategyFactory;

public class PlayerFactory {

	public static Player getComputerPlayer(TicTacToeValue value) {
		Player player = new Player("Computer", value);
		player.setStrategy(StrategyFactory.getExpertStrategy());
		return player;
	}

	public static Player getHumanPlayer(String name, TicTacToeValue value) {
		Player player = new Player(name, value);
		player.setStrategy(StrategyFactory.getNullStrategy());
		return player;
	}

}
