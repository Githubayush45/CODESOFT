import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int min = 1;
        int max = 100;
        int maxch = 7;
        int rounds = 0;
        int completed = 0;
        boolean again = true;
        while (again) {
            int guessing = (int)(Math.random() * (max - min + 1)) + min;

            int attempts = 0;
            boolean right = false;
            while (attempts < maxch) {
                System.out.print("Guess a number between " + min + " and " + max + ": ");
                int yourguess = scanner.nextInt();
                if (yourguess < guessing) {
                    System.out.println("Too low!");
                } else if (yourguess > guessing) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct!");
                    right = true;
                    rounds++;
                    break;
                }
                attempts++;
            }

            // number not corerct then  increment round
            if (!right) {
                System.out.println("You failed to guess the number. The correct number was: " + guessing);
                rounds++;
            }
            completed += attempts;
            System.out.println("Current Score: " + rounds + " rounds, " + completed + " attempts");//current score
            System.out.print("Do you want to play again? (y/n): ");// if the user wants to play again
            again = scanner.next().equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
