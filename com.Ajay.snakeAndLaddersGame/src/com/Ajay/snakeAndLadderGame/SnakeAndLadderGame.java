package com.Ajay.snakeAndLadderGame;

import java.util.*;

public class SnakeAndLadderGame {

    private static final int START_POSITION = 0;
    private static final int WINNING_POSITION = 100;
    private static final int LADDER = 1;
    private static final int SNAKE = 2;
    private int playerPosition = 0 ;
    int turn = 0;
    List<Map<Integer, Integer>> position_History = new ArrayList<>();

    // Show the current position of the player
    public void showPosition()
    {

        System.out.println("player Position :" + playerPosition );

    }

    // Simulates a player's turn by rolling the dice and moving the player
    public void playTurn()
    {
        Random random = new Random();
        int die = random.nextInt(6) + 1;
        int operator = random.nextInt(3);

        System.out.println("Die Roll :" + die);

        switch (operator) {
            case LADDER:
                System.out.println("Ladder ! Move Forward by "+die);
                playerPosition += die;
                break;
            case SNAKE:
                System.out.println("Snake! Move backward by "+die);
                playerPosition -= die;
                break;
            default:
                System.out.println("No Movement !!!");
        }

        // Adjust player's position if they exceed boundaries
        adjustPosition(die);

        System.out.println("Player current position : " + playerPosition);

    }

    // Adjusts player position to ensure it is within valid bounds
    private void adjustPosition(int die) {
        if (playerPosition < START_POSITION) {
            playerPosition = START_POSITION;
        }
        if(playerPosition > WINNING_POSITION) {
            System.out.println("Overshot! You must land exactly on position 100.!! No movement in position");
            playerPosition -= die;
        }
    }

    // Main gameplay method that handles multiple players and turns
    private void gamePlay() {

        Scanner sc = new Scanner(System.in);
        int playerNumber = 0;

        // Input validation for the number of players (2-4 players only)
        do {
            System.out.println("Enter number of players (2-4 players only)");
            playerNumber = sc.nextInt();
        }while (playerNumber < 2 || playerNumber > 4);

        // Initialize player objects and position history for each player
        SnakeAndLadderGame[] player = new SnakeAndLadderGame[playerNumber];
        boolean[] playerWon = new boolean[playerNumber];
        boolean gameOver = false;

        for(int i = 0; i < playerNumber; i++){
            player[i] = new SnakeAndLadderGame();
            position_History.add( new HashMap<>());
        }

        // Game loop to continue until a player wins
        while (!gameOver) {
            turn++;
            System.out.println("\n--- Turn " + (turn) + " ---");

            // Each player takes a turn
            for (int i = 0; i < playerNumber; i++) {

                System.out.println("\nPlayer "+(i+1)+" turn:");
                player[i].showPosition();
                player[i].playTurn();
                playerWon[i] = (player[i].playerPosition == WINNING_POSITION);

                // Store the player's position after their turn
                position_History.get(i).put(turn, player[i].playerPosition);

                // Check if the player has won
                if (playerWon[i]) {
                    gameOver = true;
                    System.out.println("\n----------------------------Player "+(i+1)+" wins!-----------------------------");
                    break;
                }
            }

        }

        // Display the position history for each player
        for (int i = 0; i < playerNumber; i++) {
            System.out.println("Player "+(i+1)+" "+ position_History.get(i).toString());
        }
    }

    public static void main(String[] args) {

        System.out.println("Welcome To Snack and Ladder Simulator");

        SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame();
        snakeAndLadderGame.gamePlay();

    }

}