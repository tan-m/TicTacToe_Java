package com.blogspot.nhu313.tictactoe.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.blogspot.nhu313.tictactoe.game.Game;

public class RestartGameListener implements ActionListener{
	private Game game;
	
	public RestartGameListener(Game game){
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		game.restart();
	}
}

