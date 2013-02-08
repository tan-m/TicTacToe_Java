package com.blogspot.nhu313.tictactoe.strategy;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;

/**
 * This class only finds the move preparing for a win or a tie. It won't block opponent from winning. The code is specific to a 3x3 board. 
 */
class StrategySecondTurnAsSecondPlayer extends Strategy {

	StrategySecondTurnAsSecondPlayer(Strategy nextStrategy) {
		super.nextStrategy = nextStrategy;
	}

	@Override
	protected Position getNextMoveFromChild(TicTacToeValue playerValue, Board board) {
		Position position = null;
		int numberOfMoves = board.getNumberOfMove();
		Position opponentPosition = board.getLastMove();

		if (numberOfMoves == 3){
			position = getPositionAsSecondPlayer(board, position, opponentPosition);
		}
		
		return position;
	}
	
	private Position getPositionAsSecondPlayer(Board board, Position position, Position opponentLastPos) {
		if (board.isCornerPosition(opponentLastPos)){
			position = getPositionWhenOpponentChoseCornerMoves(board, opponentLastPos);
		} else {
			position = getPositionWhenOpponenChoseMiddleMoves(board, opponentLastPos);			
		}
		return position;
	}

	private Position getPositionWhenOpponentChoseCornerMoves(Board board, Position opponentLastPos) {
		Position position = null;
		Position oppositeOpponentPosition = board.getOppositeCorner(opponentLastPos);
		if (board.getMoveHistory().get(0).equals(oppositeOpponentPosition)){
			position = new Position(board.getCenterSquarePosition().getRow(), 0);
		} else {
			position = getFreeCorner(board, opponentLastPos);
		}
		return position;
	}

	private Position getPositionWhenOpponenChoseMiddleMoves(Board board, Position opponentLastPos) {
		Position opponentPos1 = board.getMoveHistory().get(0);
		Position higher = null;
		Position lower = null;
		if (opponentPos1.getRow() < opponentLastPos.getRow()){
			higher = opponentPos1;
			lower = opponentLastPos;
		} else {
			higher = opponentLastPos;
			lower = opponentPos1;
		}
		
		int row = getCornerFromMiddle(lower.getRow(), higher.getRow(), board);
		int col = getCornerFromMiddle(lower.getColumn(), higher.getColumn(), board);

		return new Position(row, col);
	}

	private Position getFreeCorner(Board board, Position opponentPosition) {
		int row = opponentPosition.getRow();
		int col = board.getOppositeCorner(opponentPosition.getColumn());
		
		Position position = new Position(row, col);
		return position;
	}
	
	private int getCornerFromMiddle(int lowerPos, int higherPos, Board board){
		int corner = 0;
		if (board.isAtCorner(lowerPos)){
			corner = lowerPos;
		} else {
			corner = higherPos;
		}
		return corner;
	}
}
