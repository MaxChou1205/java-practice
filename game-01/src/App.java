import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner s = new Scanner(System.in)) {
            int min = 0;
            int max = 100;
            Random r = new Random();
            int secret = r.nextInt(max - min) + min;
            System.out.println("Secret number is: " + secret);

            while (true) {
                System.out.println("Guess a number between " + min + " and " + max);

                int guess = s.nextInt();

                if (guess < min || guess > max) {
                    System.out.println("Please enter a number between " + min + " and " + max);
                    continue;
                }

                if (guess == secret) {
                    System.out.println("Correct!");
                    break;
                } else if (guess > secret) {
                    max = guess;
                } else if (guess < secret) {
                    min = guess;
                }
            }
        }
    }
}
