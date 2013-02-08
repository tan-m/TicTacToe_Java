package com.blogspot.nhu313.tictactoe.listener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.blogspot.nhu313.tictactoe.GameProperties;
import com.blogspot.nhu313.tictactoe.game.Game;
import com.blogspot.nhu313.tictactoe.player.Player;
import com.blogspot.nhu313.tictactoe.position.Position;
import com.blogspot.nhu313.tictactoe.utils.StringUtils;

public class SquareListener implements ActionListener{
	private Game game;
	
	public SquareListener(Game game){
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		if (shouldUpdateBoard(button)){
			Position position = getSquarePosition(button);
			Player currentPlayer = game.getCurrentPlayer();
			currentPlayer.move(game.getBoard(), position);
			game.processSquareClick();
		}
	}

	private Position getSquarePosition(JButton button) {
		Point location = button.getLocation();
		int row = getPositionOnBoard(location.getY(), button.getHeight());
		int col = getPositionOnBoard(location.getX(), button.getWidth());
		Position position = new Position(row, col);
		return position;
	}

	private int getPositionOnBoard(double position, int buttonSize) {
		return (int) ((position/buttonSize)%GameProperties.BOARD_SIZE);
	}

	private boolean shouldUpdateBoard(JButton source) {
		return game.isGameInProgress() && isSquareEmpty(source);
	}

	private boolean isSquareEmpty(JButton source) {
		return StringUtils.isBlank(source.getText());
	}
}