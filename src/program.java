package SnakeAndLadder.src;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class program {

    // Snakes and Ladders on the board
    private static final Map<Integer, Integer> snakes = new HashMap<>();
    private static final Map<Integer, Integer> ladders = new HashMap<>();

    // Initializing the snakes and ladders positions
    static {
        // Snakes: Key is the head, value is the tail
        snakes.put(16, 6);
        snakes.put(47, 26);
        snakes.put(49, 11);
        snakes.put(56, 53);
        snakes.put(62, 19);
        snakes.put(64, 60);
        snakes.put(87, 24);
        snakes.put(93, 73);
        snakes.put(95, 75);
        snakes.put(98, 78);

        // Ladders: Key is the bottom, value is the top
        ladders.put(1, 38);
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(80, 100);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int positionPlayer1 = 0;
        int positionPlayer2 = 0;
        int rollCountPlayer1 = 0;
        int rollCountPlayer2 = 0;
        boolean player1Turn = true;

        System.out.println("Welcome to Snake and Ladder Game!");

        while (positionPlayer1 < 100 && positionPlayer2 < 100) {
            String currentPlayer = player1Turn ? "Player 1" : "Player 2";
            int currentPosition = player1Turn ? positionPlayer1 : positionPlayer2;
            int rollCount = player1Turn ? rollCountPlayer1 : rollCountPlayer2;

            System.out.println(currentPlayer + ", press 'r' to roll the die.");
            String input = scanner.nextLine();

            if (input.equals("r")) {
                int roll = random.nextInt(6) + 1;
                rollCount++;
                System.out.println(currentPlayer + " rolled a " + roll);

                int option = random.nextInt(3);

                switch (option) {
                    case 0: // no play
                        System.out.println("No Play. " + currentPlayer + " stays at position " + currentPosition);
                        break;

                    case 1: // ladder
                        if (currentPosition + roll <= 100) {
                            currentPosition += roll;
                            System.out.println("Ladder!! " + currentPlayer + " moved to position " + currentPosition);

                            if (snakes.containsKey(currentPosition)) {
                                currentPosition = snakes.get(currentPosition);
                                System.out.println("Oh no! " + currentPlayer + " landed on a snake. Moved down to position " + currentPosition);
                            } else if (ladders.containsKey(currentPosition)) {
                                currentPosition = ladders.get(currentPosition);
                                System.out.println("Great! " + currentPlayer + " landed on a ladder. Moved up to position " + currentPosition);
                            }

                            // Player gets another turn if they land on a ladder
                            player1Turn = !player1Turn;
                        } else {
                            System.out.println("Roll too high to move. " + currentPlayer + " stays at position " + currentPosition);
                        }
                        break;

                    case 2: // snake
                        currentPosition -= roll;
                        if (currentPosition < 0) {
                            System.out.println(currentPlayer + " moved below 0. Restarting from position 0.");
                            currentPosition = 0;
                        } else {
                            System.out.println("Snake! " + currentPlayer + " moved back to position " + currentPosition);

                            if (ladders.containsKey(currentPosition)) {
                                currentPosition = ladders.get(currentPosition);
                                System.out.println("Great! " + currentPlayer + " landed on a ladder. Moved up to position " + currentPosition);
                            } else if (snakes.containsKey(currentPosition)) {
                                currentPosition = snakes.get(currentPosition);
                                System.out.println("Oh no! " + currentPlayer + " landed on a snake. Moved down to position " + currentPosition);
                            }
                        }
                        break;
                }

                // Report position after every die roll
                System.out.println(currentPlayer + "'s current position: " + currentPosition);

                // Update the position and roll count for the current player
                if (player1Turn) {
                    positionPlayer1 = currentPosition;
                    rollCountPlayer1 = rollCount;
                } else {
                    positionPlayer2 = currentPosition;
                    rollCountPlayer2 = rollCount;
                }

                // Check if any player reached position 100
                if (positionPlayer1 == 100) {
                    System.out.println("Congratulations! Player 1 reached position 100 and won the game!");
                    System.out.println("Player 1 rolled the dice " + rollCountPlayer1 + " times to win the game.");
                    break;
                } else if (positionPlayer2 == 100) {
                    System.out.println("Congratulations! Player 2 reached position 100 and won the game!");
                    System.out.println("Player 2 rolled the dice " + rollCountPlayer2 + " times to win the game.");
                    break;
                }

                // If the player did not land on a ladder, switch turns
                if (!ladders.containsKey(currentPosition)) {
                    player1Turn = !player1Turn;
                }
            } else {
                System.out.println("Invalid input. Please press 'r' to roll the die.");
            }
        }
        scanner.close();
    }
}

