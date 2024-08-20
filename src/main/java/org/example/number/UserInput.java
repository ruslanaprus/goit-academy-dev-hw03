package org.example.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UserInput implements NumberGetter {
    private static final Logger logger = LoggerFactory.getLogger(UserInput.class);
    private final NumberValidator numberValidator;

    public UserInput(){
        this.numberValidator = new NumberValidator();
    }

    @Override
    public int get() {
        try (Scanner scanner = new Scanner(System.in)) {
            int number;
            do {
                logger.info("Please enter a number: ");
                number = getUserInput(scanner);
            } while (!numberValidator.isValidNumber(number));
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
}
