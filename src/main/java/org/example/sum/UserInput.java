package org.example.sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static org.example.constants.Constants.MAX_VALUE;
import static org.example.constants.Constants.MIN_VALUE;

public class UserInput implements NumberGetter {
    private static final Logger logger = LoggerFactory.getLogger(UserInput.class);

    @Override
    public int get() {
        try (Scanner scanner = new Scanner(System.in)) {
            int number;
            do {
                logger.info("Please enter a number: ");
                number = getUserInput(scanner);
            } while (!isValidNumber(number));
            return number;
        }
    }

    private int getUserInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            logger.warn("Invalid input provided by the user.");
            logger.info("Invalid input. Please enter a valid positive number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private boolean isValidNumber(int number) {
        if (number >= MIN_VALUE && number <= MAX_VALUE) {
            return true;
        } else {
            logger.warn("Number out of range. Please enter a number between 1 and 65535");
            return false;
        }
    }
}
