package com.blogspot.nhu313.tictactoe.strategy;

public class StrategyFactory {

	/**
	 * The strategy uses a decorator pattern to determine the best move to make.
	 * This method creates a linked strategy with the first StrategyFirstTurn as the first class. 
	 * The order of these strategy matters because they depended on the previous strategy 
	 * to do all the necessary check. For example, when the computer is the first player, 
	 * then on the second turn, there is no need to check if the computer needs to block 
	 * the opponent move. But if the computer is the second player then there is a chance 
	 * the computer need to block the opponent from winning. That's why the block strategy 
	 * run first.
	 * @return
	 */
	public static Strategy getExpertStrategy() {
		Strategy nextAvailableSquare = new StrategyNextAvailableSquare(null);
		Strategy thirdTurn = new StrategyThirdTurn(nextAvailableSquare);
		Strategy secondTurnAsSecondPlayer = new StrategySecondTurnAsSecondPlayer(thirdTurn);
		Strategy block = new StrategyBlock(secondTurnAsSecondPlayer);
		Strategy win = new StrategyWin(block);
		Strategy secondTurnAsFirstPlayer = new StrategySecondTurnAsFirstPlayer(win);
		Strategy firstTurn = new StrategyFirstTurn(secondTurnAsFirstPlayer);
		return firstTurn;
	}

	public static Strategy getNullStrategy() {
		Strategy strategy = new StrategyNull();
		return strategy;
	}

}
