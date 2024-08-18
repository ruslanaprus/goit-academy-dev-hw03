package org.example.sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UserInput implements NumberGetter {
    private static final Logger logger = LoggerFactory.getLogger(UserInput.class);

    @Override
    public int get() {
        try (Scanner scanner = new Scanner(System.in)) {
            int number;
            do {
                logger.info("Please enter a positive number: ");
                while (!scanner.hasNextInt()) {
                    logger.warn("Invalid input provided by the user.");
                    logger.info("Invalid input. Please enter a valid positive number: ");
                    scanner.next();
                }
                number = scanner.nextInt();
            } while (number <= 0);
            return number;
        }
    }
}
