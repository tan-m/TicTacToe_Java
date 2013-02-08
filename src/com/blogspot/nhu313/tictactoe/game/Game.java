package com.blogspot.nhu313.tictactoe.game;

import com.blogspot.nhu313.tictactoe.player.Player;
import com.blogspot.nhu313.tictactoe.tools.WinCalculator;


public class Game {

	public static final String IT_S_A_TIE_MSG = "It's a tie!";
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Player firstPlayer;
	private Board board;
	private WinCalculator winCalculator;
	private boolean gameInProgress;
	
	public Game(int boardSize){
		board = new Board(boardSize);
		winCalculator = new WinCalculator(board);
	}

	public void setPlayers(Player... player) {
		if (player == null || player.length < 2){
			throw new IllegalArgumentException("Must have more than one player.");
		}
		
		player1 = player[0];
		player2 = player[1];

		currentPlayer = player1;
	}

	public void restart() {
		board.cleanBoard();
		if (isGameInProgress()){
			currentPlayer = firstPlayer;
		}
		changeCurrentPlayer();
		start();
	}
	
	public void start(){
		firstPlayer = currentPlayer;
		gameInProgress = true;
		updateScores();
		moveNewPlayer();
	}

	public void moveNewPlayer() {
		while (isGameInProgress() && currentPlayer.move(board)){
			processSquareClick();
		}
	}
	
	public Board getBoard() {
		return this.board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void processSquareClick() {
		if (hasPlayerWon()){
			endGame(currentPlayer.getName() + " won!");
			currentPlayer.win();
			updateScores();
		} else if (isATie()){
			endGame(IT_S_A_TIE_MSG);
		} else {
			changeCurrentPlayer();
			moveNewPlayer();
		}
	}

	private void updateScores() {
		board.setScoreMessage(player1.getScoreMessage() + "  |  " + player2.getScoreMessage());
	}

	protected boolean isATie() {
		return board.isFilled();
	}

	private void endGame(String message) {
		board.setMessage(message);
		gameInProgress = false;
	}
	
	public boolean isGameInProgress(){
		return gameInProgress;
	}

	private void changeCurrentPlayer() {
		if (currentPlayer.equals(player1)){
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
		
	}

	private boolean hasPlayerWon() {
		return winCalculator.hasPlayerWon(currentPlayer);
	}
}
