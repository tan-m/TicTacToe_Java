package com.blogspot.nhu313.tictactoe.strategy;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

class StrategyWin extends Strategy {

	protected static final int INVALID_POSITION = -1;

	StrategyWin(Strategy nextStrategy){
		super.nextStrategy = nextStrategy;
	}

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		Position position = getDiagonalWinPosition(playerValue, board);
		if (position == null){
			position = getVerticalWinPosition(playerValue, board);
			if (position == null){
				position = getHorizontalWinPosition(playerValue, board);
			}
		}
		return position;
	}

	protected Position getHorizontalWinPosition(TicTacToeValue playerValue, Board board) {
		Position position = null;
		for (int i = 0; i < board.size(); i++){
			TicTacToeValue[] values = board.getRowValues(i);
			int col = getWinningPosition(playerValue, values);
			if (col != INVALID_POSITION){
				position = new Position(i, col);
				break;
			}
		}
		return position;
	}

	protected Position getVerticalWinPosition(TicTacToeValue playerValue, Board board) {
		Position position = null;
		for (int i = 0; i < board.size(); i++){
			TicTacToeValue[] values = board.getColumnValues(i);
			int row = getWinningPosition(playerValue, values);
			if (row != INVALID_POSITION){
				position = new Position(row, i);
				break;
			}
		}
		return position;
	}

	private Position getDiagonalWinPosition(TicTacToeValue playerValue, Board board) {
		Position position = getLeftDiagonalWinPosition(playerValue, board);
		if (position == null){
			position = getRightDiagonalWinPosition(playerValue, board);
		}
		return position;
	}

	protected Position getRightDiagonalWinPosition(TicTacToeValue playerValue, Board board) {
		TicTacToeValue[] values = board.getRightDiagonalValues();
		int position = getWinningPosition(playerValue, values);
		Position pos = null;
		if (position != INVALID_POSITION){
			pos = new Position(position, board.size() - 1 - position);
		}
		return pos;
	}

	protected Position getLeftDiagonalWinPosition(TicTacToeValue playerValue, Board board) {
		TicTacToeValue[] values = board.getLeftDiagonalValues();
		int position = getWinningPosition(playerValue, values);
		Position pos = null;
		if (position != INVALID_POSITION){
			pos = new Position(position, position);
		}
		return pos;
	}

	protected int getWinningPosition(TicTacToeValue playerValue, TicTacToeValue[] values) {
		int position = INVALID_POSITION;
		int playerValueCount = 0;
		for (int i = 0; i < values.length; i++){
			TicTacToeValue value = values[i];
			if (value == null){
				position = i;
			} else if (value.equals(playerValue)){
				playerValueCount++;
			} else {
				break;
			}
		}
		
		if (!hasOneEmptyValue(playerValueCount, values.length)){
			position = INVALID_POSITION;
		}
		
		return position;
	}

	private boolean hasOneEmptyValue(int playerValueCount, int numOfValues) {
		return playerValueCount == (numOfValues - 1);
	}

}
