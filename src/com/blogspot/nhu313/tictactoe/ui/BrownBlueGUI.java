package com.blogspot.nhu313.tictactoe.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blogspot.nhu313.tictactoe.GameProperties;
import com.blogspot.nhu313.tictactoe.game.Board;
import com.blogspot.nhu313.tictactoe.game.Game;
import com.blogspot.nhu313.tictactoe.listener.RestartGameListener;
import com.blogspot.nhu313.tictactoe.listener.SquareListener;

public class BrownBlueGUI {
	
	private final static int FRAME_SIZE = 450;

	private final static String FONT = "Arial";
	
	private final static Color BLUE = new Color(0, 172, 193);
	private final static Color BROWN = new Color(120, 100, 100);
	private final static Color ORANGE = new Color(222, 80, 4);

	private JFrame frame;
	
	public void displayGUI(){
		frame.setVisible(true);
	}
	
	public void createGUI(Game game) {
        frame = createFrame();
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(getTitle());
        content.add(getGameMessage(game.getBoard()));
        content.add(getSquarePanel(game));
        content.add(getScorePanel(game.getBoard()));
        content.add(getNewGamePanel(game));
        frame.setVisible(true);
    }

	private JFrame createFrame() {
		JFrame frame = new JFrame(GameProperties.GAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
		return frame;
	}
	
	private Component getTitle() {
		JLabel title = new JLabel(GameProperties.GAME_NAME);
		title.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BLUE));
		setCommonComponentProperty(title, 50, BROWN, Font.BOLD);
		return title;
	}

	private Component getGameMessage(Board board) {
		JLabel gameMessage = board.getMessage();
		gameMessage.setBorder(BorderFactory.createEmptyBorder());
		setCommonComponentProperty(gameMessage, 20, ORANGE, Font.PLAIN);
		return gameMessage;
	}
	
	private JPanel getSquarePanel(Game game) {
		Board board = game.getBoard();
		JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(GameProperties.BOARD_SIZE, GameProperties.BOARD_SIZE));
		for (int row = 0; row < GameProperties.BOARD_SIZE; row++){
			for (int col = 0; col < GameProperties.BOARD_SIZE; col++){
				JButton square = board.getSquare(row, col);
				square.addActionListener(new SquareListener(game));
				square.setContentAreaFilled(false);
				square.setBorder(BorderFactory.createEtchedBorder());
				setCommonComponentProperty(square, 50, BLUE, Font.BOLD);

				panel.add(square);
			}
		}
		return panel;
	}

	private Component getScorePanel(Board board) {
		JLabel message = board.getScoreMessage();
		message.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		setCommonComponentProperty(message, 20, ORANGE, Font.PLAIN);
		return message;
	}

	private Component getNewGamePanel(Game game) {
		JButton button = new JButton("NEW GAME");
		button.addActionListener(new RestartGameListener(game));
		button.setBackground(BROWN);
		setCommonComponentProperty(button, 30, Color.WHITE, Font.BOLD);
		return button;
	}
	
	private void setCommonComponentProperty(JComponent component, int fontSize, Color fontColor, int fontWeight){
		component.setFont(new Font(FONT, Font.PLAIN, fontSize));
		component.setForeground(fontColor);
		component.setAlignmentX(Component.CENTER_ALIGNMENT);
		component.setFocusable(false);
	}
}
