package com.blogspot.nhu313.tictactoe.strategy;

import java.util.List;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

class StrategyThirdTurn extends Strategy {
	
	StrategyThirdTurn(Strategy nextStrategy){
		super.nextStrategy = nextStrategy;
	}

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		Position position = null;
		if (isFirstPlayerThirdTurn(board)){
			List<Position> moveHistory = board.getMoveHistory();
			Position opponentSecondMove = board.getLastMove();
			Position opponentFirstMove = moveHistory.get(1);

			int row = 0;
			int col = 0;
			
			int diff = getAbsoluteDifference(opponentFirstMove.getRow(), opponentSecondMove.getRow());
			if (diff == board.getEndCorner()){
				row = opponentFirstMove.getRow();
				col = board.getOppositeCorner(opponentFirstMove.getColumn());
			} else {
				row = board.getOppositeCorner(opponentFirstMove.getRow());
				col = opponentFirstMove.getColumn();				
			}
				
			position = new Position(row, col);
		}
		
		return position;
	}

	private int getAbsoluteDifference(int num1, int num2) {
		return Math.abs(num1 - num2);
	}

	private boolean isFirstPlayerThirdTurn(Board board) {
		return board.getNumberOfMove() == 4;
	}
}
