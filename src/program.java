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
        int position = 0;

        System.out.println("Welcome to Snake and Ladder Game!");

        while (position < 100) {
            System.out.println("Press 'r' to roll the die.");
            String input = scanner.nextLine();

            if (input.equals("r")) {
                int roll = random.nextInt(100) + 1;
                System.out.println("You rolled a " + roll);

                if (position + roll <= 100) {
                    position += roll;
                    System.out.println("You moved to position " + position);

                    if (snakes.containsKey(position)) {
                        position = snakes.get(position);
                        System.out.println("Oh no! You landed on a snake. Move down to position " + position);
                    } else if (ladders.containsKey(position)) {
                        position = ladders.get(position);
                        System.out.println("Great! You landed on a ladder. Move up to position " + position);
                    }
                } else {
                    System.out.println("Roll too high to move. Stay at position " + position);
                }

                if (position == 100) {
                    System.out.println("Congratulations! You reached position 100 and won the game!");
                    break;
                }
            } else {
                System.out.println("Invalid input. Please press 'r' to roll the die.");
            }
        }

        scanner.close();
    }
}
