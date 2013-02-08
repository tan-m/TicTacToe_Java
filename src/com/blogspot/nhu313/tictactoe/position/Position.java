package com.blogspot.nhu313.tictactoe.position;

public class Position {

	private final int row;
	private final int column;
	
	public Position(int row, int column) {
		super();
		if (row < 0 || column < 0){
			throw new IllegalArgumentException("Position cannot be less than 0");
		}
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
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
		Position other = (Position) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}	
	
	public String toString(){
		return "[" + row + ',' + column + ']';
	}
}
