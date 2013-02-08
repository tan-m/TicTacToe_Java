package com.blogspot.nhu313.tictactoe.player;

import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.TicTacToeValue;
import com.blogspot.nhu313.tictactoe.position.Position;
import com.blogspot.nhu313.tictactoe.strategy.Strategy;
import com.blogspot.nhu313.tictactoe.utils.StringUtils;

public class Player {
	
	private final TicTacToeValue value;
	private final String name;
	private Strategy strategist;
	private int score;

	Player(String name, TicTacToeValue value) {
		if (value == null){
			new IllegalArgumentException("Value cannot be null.");
		}
		this.value = value;
		this.name = StringUtils.getNonNullString(name);
	}
	
	public String getName(){
		return name;
	}
	
	public TicTacToeValue getValue(){
		return value;
	}

	protected void setStrategy(Strategy strategist){
		this.strategist = strategist;
	}
	
	/**
	 * @param board
	 * @return true if the player moved, false otherwise
	 */
	public boolean move(Board board) {
		Position position = strategist.getNextMove(getValue(), board);
		return markBoard(board, position);
	}
	
	public boolean move(Board board, Position position){
		return markBoard(board, position);
	}

	private boolean markBoard(Board board, Position position) {
		boolean moved = false;
		if (shouldMarkBoard(board, position)){
			board.markSquare(getValue(), position);
			moved = true;
		}
		return moved;
	}

	private boolean shouldMarkBoard(Board board, Position position) {
		return position != null && board.isSquareAvailable(position);
	}

	public void win() {
		score++;
	}
	
	public int getScore(){
		return score;
	}
	
	public String getScoreMessage() {
		return name + " [" + value.toString() + "] : " + score; 
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		result = prime * result
				+ ((strategist == null) ? 0 : strategist.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		if (strategist == null) {
			if (other.strategist != null)
				return false;
		} else if (!strategist.equals(other.strategist))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player name=" + name + ", value " + value + ", score=" + score;
	}

}