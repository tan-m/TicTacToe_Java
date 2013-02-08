package com.blogspot.nhu313.tictactoe.strategy;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

class StrategyNull extends Strategy{

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		return null;
	}

}
