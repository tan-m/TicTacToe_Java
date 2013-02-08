package com.blogspot.nhu313.tictactoe.game;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.blogspot.nhu313.tictactoe.position.Position;
import com.blogspot.nhu313.tictactoe.utils.StringUtils;


public class Board {
	//Need a space because UI doesn't reserve space for the text when the text is empty
	//so the frame resize when the text change
	private static final String DEFAULT_SQUARE_TEXT = " ";

	private JLabel gameMessage;
	private JLabel scoreMessage;
	private JButton[][] boardSquares;
	private List<Position> moveHistory;
	private final Position centerPosition;
	
	public Board(int size){
		initializeSquares(size);
		gameMessage = new JLabel(DEFAULT_SQUARE_TEXT);
		moveHistory = new LinkedList<Position>();
		scoreMessage = new JLabel(DEFAULT_SQUARE_TEXT);
		centerPosition = new Position(size()/2, size()/2);
	}

	private void initializeSquares(int size) {
		boardSquares = new JButton[size][size];
		for (int col = 0; col < size; col++){
			for (int row = 0; row < size; row++){
				JButton button = new JButton(DEFAULT_SQUARE_TEXT);
				boardSquares[row][col] = button;
			}
		}
	}
	
	public JLabel getScoreMessage() {
		return scoreMessage;
	}

	public void setScoreMessage(String message) {
		this.scoreMessage.setText(message);
	}

	public JLabel getMessage(){
		return gameMessage;
	}

	public void setMessage(String message){
		gameMessage.setText(message);
	}

	public int size(){
		return boardSquares.length;
	}

	public boolean isEmpty() {
		return getNumberOfMove() == 0;
	}

	public boolean isFilled() {
		return getNumberOfMove() == (int)(Math.pow(size(), 2));
	}
	
	public int getNumberOfMove(){
		return moveHistory.size();
	}
	
	public List<Position> getMoveHistory(){
		return moveHistory;
	}

	public Position getLastMove() {
		Position position = null;
		if (!isEmpty()){
			position = moveHistory.get(moveHistory.size() - 1);
		}
		return position;
	}

	public Position getCenterSquarePosition(){
		return centerPosition;
	}
	
	public JButton getSquare(int row, int col) {
		return boardSquares[row][col];
	}

	public TicTacToeValue getSquareValue(Position position){		
		JButton square = boardSquares[position.getRow()][position.getColumn()];
		String squareText = square.getText();
		TicTacToeValue value = getTicTacToeValue(squareText);		
		return value;
	}
	
	public boolean isSquareAvailable(Position position){
		if (position == null){
			return false;
		}
		
		return getSquareValue(position) == null;
	}

	private TicTacToeValue getTicTacToeValue(String squareText) {
		TicTacToeValue value = null;
		if (StringUtils.isNotBlank(squareText)){
			value = TicTacToeValue.valueOf(squareText);
		}
		return value;
	}

	public void markSquare(TicTacToeValue value, Position position){
		moveHistory.add(position);
		JButton square = boardSquares[position.getRow()][position.getColumn()];
		square.setText(getCleanValue(value));
	}

	private String getCleanValue(TicTacToeValue value) {
		String cleanValue = null;
		if (value == null){
			cleanValue = DEFAULT_SQUARE_TEXT;
		} else {
			cleanValue = value.toString();
		}
		return cleanValue;
	}

	public TicTacToeValue[] getRowValues(int rowNum){
		JButton[] row = boardSquares[rowNum];
		return getSquareValues(row);
	}

	public TicTacToeValue[] getColumnValues(int colNum){
		final int boardSize = size();
		JButton[] col = new JButton[boardSize];
		for (int i = 0; i < boardSize; i++){
			col[i] = boardSquares[i][colNum];
		}
		return getSquareValues(col);
	}


	public TicTacToeValue[] getLeftDiagonalValues(){
		final int boardSize = size();
		JButton[] leftDiagonalSquares = new JButton[boardSize];
		for (int i = 0; i < boardSize; i++){
			leftDiagonalSquares[i] = boardSquares[i][i];
		}
		return getSquareValues(leftDiagonalSquares);
	}

	public TicTacToeValue[] getRightDiagonalValues(){
		final int boardSize = size();
		JButton[] rightDiagonalSquares = new JButton[boardSize];
		for (int i = 0; i < boardSize; i++){
			rightDiagonalSquares[i] = boardSquares[i][getEndCorner() - i]; 
		}
		return getSquareValues(rightDiagonalSquares);
	}

	private TicTacToeValue[] getSquareValues(JButton[] row) {
		TicTacToeValue[] values = new TicTacToeValue[row.length];
		for (int i = 0; i < row.length; i++){
			String squareText = row[i].getText();
			values[i] = getTicTacToeValue(squareText);
		}
		return values;
	}
	
	public boolean isCornerPosition(Position position) {
		boolean rowCorner = isAtCorner(position.getRow());
		boolean colCorner = isAtCorner(position.getColumn());
		return rowCorner && colCorner;
	}

	public boolean isAtCorner(int number) {
		return number == 0 || number == getEndCorner();
	}
	
	public Position getOppositeCorner(Position position){
		int row = getOppositeCorner(position.getRow());
		int col = getOppositeCorner(position.getColumn());
		
		return new Position(row, col);
	}
	
	public int getOppositeCorner(int pos) {
		if (pos == 0){
			pos = getEndCorner();
		} else {
			pos = 0;
		}
		return pos;
	}

	public int getEndCorner(){
		return size() - 1;
	}
	
	public void cleanBoard(){
		moveHistory = new LinkedList<Position>();
		setMessage(DEFAULT_SQUARE_TEXT);
		clearSquareValues();
	}

	private void clearSquareValues() {
		int boardSize = size();
		for (int col = 0; col < boardSize; col++){
			for (int row = 0; row < boardSize; row++){
				boardSquares[row][col].setText(DEFAULT_SQUARE_TEXT);
			}
		}
	}
	
	public void printBoard(PrintStream printStream){
		final int size = size();

		for (int row = 0; row < size; row++){
			for (int col = 0; col < size; col++){
				TicTacToeValue squareValue = getSquareValue(new Position(row, col));
				String value = getCleanValue(squareValue);
				printStream.print('['+ value +']');
			}
			printStream.print('\n');
		} 
	}
}