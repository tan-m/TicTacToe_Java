package com.blogspot.nhu313.tictactoe.strategy;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

class StrategySecondTurnAsFirstPlayer extends Strategy {

	StrategySecondTurnAsFirstPlayer(Strategy nextStrategy) {
		super.nextStrategy = nextStrategy;
	}

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		Position position = null;
		int numberOfMoves = board.getNumberOfMove();
		Position opponentPosition = board.getLastMove();

		if (numberOfMoves == 2){
			position = getPositionAsFirstPlayer(board, opponentPosition);
		}
		
		return position;
	}
	
	private Position getPositionAsFirstPlayer(Board board, Position opponentPosition) {
		Position position = null;
		if (board.isCornerPosition(opponentPosition)){
			position = board.getOppositeCorner(opponentPosition);
		} else {
			position = getOpposingCornerForOuterMiddlePosition(opponentPosition, board);
		}
		return position;
	}

	private Position getOpposingCornerForOuterMiddlePosition(Position position, Board board) {
		int row = getCornerForOuterMiddlePosition(board, position.getRow());
		int col = getCornerForOuterMiddlePosition(board, position.getColumn());
		return new Position(row, col);
	}

	private int getCornerForOuterMiddlePosition(Board board, int num) {
		if (num == board.size()/2){
			num = 0;
		} else {
			num = board.getOppositeCorner(num);
		}
		return num;
	}
}
