package com.blogspot.nhu313.tictactoe.strategy;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

class StrategyBlock extends Strategy{
	private final static Strategy WIN_STRATEGY = new StrategyWin(null);
	
	StrategyBlock(Strategy nextStrategy){
		super.nextStrategy = nextStrategy;
	}	

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		TicTacToeValue opponentValue = TicTacToeValue.getOpponentValue(playerValue);
		return WIN_STRATEGY.getNextMoveFromChild(opponentValue, board);
	}
}
