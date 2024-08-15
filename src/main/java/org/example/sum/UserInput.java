package org.example.sum;

import java.util.Scanner;

public class UserInput implements NumberGetter {
    @Override
    public int get() {
        try (Scanner scanner = new Scanner(System.in)) {
            int number;
            do {
                System.out.print("Please enter a positive number: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Please enter a valid positive number: ");
                    scanner.next();
                }
                number = scanner.nextInt();
            } while (number <= 0);
            return number;
        }
    }
}
