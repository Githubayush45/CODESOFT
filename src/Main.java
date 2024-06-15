import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define the range for the random number
        int minNum = 1;
        int maxNum = 100;

        // Define the maximum number of attempts
        int maxAttempts = 6;

        // Initialize the score
        int totalRounds = 0;
        int totalAttempts = 0;

        // Play multiple rounds
        boolean playAgain = true;
        while (playAgain) {
            // Generate a random number within the specified range
            int secretNum = (int)(Math.random() * (maxNum - minNum + 1)) + minNum;

            // Initialize the number of attempts for the current round
            int attempts = 0;
            boolean guessedCorrectly = false;

            // Play a round
            while (attempts < maxAttempts) {
                // Prompt the user to enter their guess
                System.out.print("Guess a number between " + minNum + " and " + maxNum + ": ");
                int userGuess = scanner.nextInt();

                // Compare the user's guess with the secret number
                if (userGuess < secretNum) {
                    System.out.println("Too low!");
                } else if (userGuess > secretNum) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct!");
                    guessedCorrectly = true;
                    totalRounds++;
                    break;
                }

                attempts++;
            }

            // If the user did not guess the number correctly, increment the round counter
            if (!guessedCorrectly) {
                totalRounds++;
            }

            // Add the attempts for the current round to the total attempts
            totalAttempts += attempts;

            // Display the current score
            System.out.println("Current Score: " + totalRounds + " rounds, " + totalAttempts + " attempts");

            // Ask if the user wants to play again
            System.out.print("Do you want to play again? (y/n): ");
            playAgain = scanner.next().equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing!");

        // Close the scanner
        scanner.close();
    }
}
