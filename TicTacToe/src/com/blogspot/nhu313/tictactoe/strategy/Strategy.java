package com.blogspot.nhu313.tictactoe.strategy;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

/**
 * This class uses the decorator pattern to determine the next move to make. 
 * The level of difficulty will be determined by the linkage of the strategies. 
 * If you do not wish to use a strategy, call StrategyFactory to get a null strategy.
 */
public abstract class Strategy {

	protected Strategy nextStrategy;
	
	public Position getNextMove(TicTacToeValue playerValue, Board board){
		Position position = getNextMoveFromChild(playerValue, board);
		if (shouldGetNextStrategyMove(position, board)){
			position = nextStrategy.getNextMove(playerValue, board);
		}
		return position;
	}

	private boolean shouldGetNextStrategyMove(Position position, Board board) {
		if (board.isSquareAvailable(position)){
			return false;
		}
		
		if (nextStrategy == null){
			return false;
		}
				
		return true;
	}

	protected abstract Position getNextMoveFromChild(TicTacToeValue playerValue,Board board);
	
}
