package com.blogspot.nhu313.tictactoe.game;

public enum TicTacToeValue {
	X,O;
	
	public static TicTacToeValue getOpponentValue(TicTacToeValue player){
		TicTacToeValue opponent = null;
		if (player.equals(X)){
			opponent = O;
		} else {
			opponent = X;
		}
		
		return opponent;
	}
}
