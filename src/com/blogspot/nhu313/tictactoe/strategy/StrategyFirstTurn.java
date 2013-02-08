package com.blogspot.nhu313.tictactoe.strategy;

import java.util.List;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

class StrategyFirstTurn extends Strategy {
	private static final int NUMBER_OF_CORNER_ON_BOARD = 2;

	StrategyFirstTurn(Strategy nextStrategy){
		super.nextStrategy = nextStrategy;
	}

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		Position position = null;
		if (board.getNumberOfMove() < 2){
			List<Position> moveHistory = board.getMoveHistory();
			Position centerSquarePosition = board.getCenterSquarePosition();
			if (moveHistory.contains(centerSquarePosition)){
				position = getRandomSquareCorner(board.size());
			} else {
				position = centerSquarePosition;
			}
		}
		
		return position;
	}
	
	protected Position getRandomSquareCorner(int boardSize) {
		int row = getRandomCornerNumber(boardSize);
		int col = getRandomCornerNumber(boardSize);
		return new Position(row, col);
	}

	private int getRandomCornerNumber(int boardSize) {
		int randomNumber = getRandomNumber();
		int number = randomNumber % NUMBER_OF_CORNER_ON_BOARD;
		if (number > 0){
			number = boardSize - 1;
		}
		return number;
	}

	protected int getRandomNumber() {
		int randomNumber = (int) (Math.random() * 10);
		return randomNumber;
	}
}