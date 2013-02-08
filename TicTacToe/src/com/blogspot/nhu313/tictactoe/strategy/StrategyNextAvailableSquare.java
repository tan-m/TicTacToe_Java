package com.blogspot.nhu313.tictactoe.strategy;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

class StrategyNextAvailableSquare extends Strategy{
	
	StrategyNextAvailableSquare(Strategy NextStrategy){
		super.nextStrategy = NextStrategy;
	}

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		Position position = null;

		final int boardSize = board.size();
		for (int row = 0; row < boardSize; row++){
			for (int col = 0; col < boardSize; col++){
				Position pos = new Position(row, col);
				if (board.isSquareAvailable(pos)){
					position = pos;
					break;
				}
			}
		}
		return position;
	}

}
