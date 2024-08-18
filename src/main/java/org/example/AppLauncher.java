package org.example;

import org.example.sum.*;
import org.example.sum.NumberGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLauncher {
    private static final Logger logger = LoggerFactory.getLogger(AppLauncher.class);

    public static void main(String[] args) {
        try {
//        NumberGetter userInput = new UserInput();
//        NumberManager numberManager = new NumberManager(userInput);
//        NumberGetter randomInt = new RandomInt();
//        NumberManager numberManager = new NumberManager(randomInt);
            NumberGetter numberFromFile = NumberFromFile.getInstance("src/main/resources/number.txt");
            NumberManager numberManager = new NumberManager(numberFromFile);
            int number = numberManager.getNumericValue();
            logger.info("Retrieved number: {}", number);
            SumCalculator sumCalculator = new SumCalculator();
            logger.info("Sum of numbers: {}", sumCalculator.sum(number));
        } catch (IllegalStateException | IllegalArgumentException e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
    }
}