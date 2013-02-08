package com.blogspot.nhu313.tictactoe.tools;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.player.Player;


public class WinCalculator {
	
	private Board board;
	
	public WinCalculator(Board board){
		this.board = board;
	}

	public boolean hasPlayerWon(Player player){
		boolean win = false;
		final TicTacToeValue playerValue = player.getValue();
		if (winDiagonally(playerValue)){
			win = true;
		} else if (winVertially(playerValue)){
			win = true;
		} else if (winHorizontally(playerValue)){
			win = true;
		}
		
		return win;
	}

	private boolean winDiagonally(TicTacToeValue playerValue) {
		boolean win = winLeftDiagonal(playerValue);
		if (!win){
			win = winRightDiagonal(playerValue);
		}
		return win;
	}

	private boolean winRightDiagonal(TicTacToeValue playerValue) {
		TicTacToeValue[] rightDiagonalValues = board.getRightDiagonalValues();
		return isValuesSameAsPlayerValue(playerValue, rightDiagonalValues);
	}

	private boolean winLeftDiagonal(TicTacToeValue playerValue) {
		TicTacToeValue[] leftDiagonalValues = board.getLeftDiagonalValues();
		return isValuesSameAsPlayerValue(playerValue, leftDiagonalValues);
	}

	private boolean winVertially(final TicTacToeValue playerValue) {
		boolean colWin = false;
		for (int col = 0; col < board.size(); col++){
			TicTacToeValue[] colValues = board.getColumnValues(col);
			colWin = isValuesSameAsPlayerValue(playerValue, colValues);
			if (colWin){
				break;
			}
		}
		
		return colWin;
	}

	private boolean winHorizontally(final TicTacToeValue playerValue) {
		boolean rowWin = false;
		for (int row = 0; row < board.size(); row++){
			TicTacToeValue[] rowValues = board.getRowValues(row);
			rowWin = isValuesSameAsPlayerValue(playerValue, rowValues);
			if (rowWin){
				break;
			}
		}
		
		return rowWin;
	}
	
	
	private boolean isValuesSameAsPlayerValue(final TicTacToeValue playerValue, TicTacToeValue[] squareValues){
		boolean win = true;
		for (int i = 0; i < squareValues.length; i++){
			if (!playerValue.equals(squareValues[i])){
				win = false;
				break;
			}
		}
		return win;
	}
}
