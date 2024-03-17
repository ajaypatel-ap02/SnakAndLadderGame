package snakeAndLadderGame;

import java.util.Random;

public class SnakeAndLadder {
	
	private int playerPosition = 0 ;
	
	public void showPosition()
	{
		System.out.println("player Position :" + playerPosition );
	}
	
	public void playGame()
	{
		Random random = new Random();
		int die = random.nextInt(6)+1;
		
		System.out.println("Die No :" + die);
	}
	
	public static void main(String[] args) {
	
		System.out.println("Welcome To Snack and Ladder Simulator");
		
		SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
		snakeAndLadder.showPosition();
		snakeAndLadder.playGame();
	}

}