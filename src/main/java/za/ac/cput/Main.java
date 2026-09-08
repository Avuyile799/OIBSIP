package za.ac.cput;

import java.util.Random;
import java.util.Scanner;

public class Main {
public static void main(String[] args){

 Scanner scanner = new Scanner(System.in);
  Random random = new Random();

   int round = 1;
   int totalScore = 0;
   boolean playAgain = true;

   while (playAgain) {
 System.out.println("\nChoose Difficulty:");
 System.out.println("1. Easy   (1-50, 10 attempts)");
 System.out.println("2. Medium (1-100, 7 attempts)");
 System.out.println("3. Hard   (1-200, 5 attempts)");

 System.out.print("Enter your choice: ");
 int difficulty = scanner.nextInt();

            int maxNumber;
            int maxAttempts;

            if (difficulty == 1) {
                maxNumber = 50;
                maxAttempts = 10;
            } else if (difficulty == 2) {
                maxNumber = 100;
                maxAttempts = 7;
            } else if (difficulty == 3) {
                maxNumber = 200;
                maxAttempts = 5;
            } else {
                System.out.println("Invalid choice. Medium difficulty selected.");
                maxNumber = 100;
                maxAttempts = 7;
            }

 int secretNumber = random.nextInt(maxNumber) + 1;
 int attempts = 0;
 boolean guessedCorrectly = false;

 System.out.println("\n==============================");
 System.out.println("     NUMBER GUESSING GAME");
 System.out.println("==============================");
 System.out.println("Round " + round);
 System.out.println("I have chosen a number between 1 and " + maxNumber + ".");
 System.out.println("You have " + maxAttempts + " attempts.");
 System.out.println();

 while (attempts < maxAttempts && !guessedCorrectly) {

     System.out.print("Enter your guess: ");

     int guess = scanner.nextInt();
                attempts++;
     if (guess < secretNumber) {
       System.out.println("Too Low!");

       } else if (guess > secretNumber) {
     System.out.println("Too High!");

     } else {
      guessedCorrectly = true;

       int score = (maxAttempts - attempts +1) * 10;
                    totalScore += score;

        System.out.println("Correct!");
        System.out.println("You guessed the number in " + attempts + " attempts.");
        System.out.println("Round Score: " + score);
                }

     System.out.println("Attempts remaining: " + (maxAttempts - attempts));

            }

            if (!guessedCorrectly) {
                System.out.println("\nYou Lost!");
                System.out.println("The number was: " + secretNumber);
            }
            if (guessedCorrectly) {
                System.out.println("\nRound " + round
                        + " — guessed in " + attempts + " attempts.");
            } else {
                System.out.println("\nRound " + round + " — You Lost!");
            }

            System.out.println("Total Score: " + totalScore);

            System.out.print("Would you like to play again? (yes/no): ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("yes")) {
                round++;
            } else {
                playAgain = false;
            }
        }
        System.out.println("\nThank you for playing!");
        scanner.close();


            }
    }

