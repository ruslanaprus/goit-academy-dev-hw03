package org.example;

import org.example.sum.*;
import org.example.sum.NumberGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLauncher {
    private static final Logger logger = LoggerFactory.getLogger(AppLauncher.class);

    public static void main(String[] args) {
        AppLauncher appLauncher = new AppLauncher();
        appLauncher.launch(new RandomInt(), new SumCalculator());
        appLauncher.launch(NumberFromFile.getInstance("src/main/resources/number.txt"), new SumCalculator());
        appLauncher.launch(new UserInput(), new SumCalculator());
    }

    public void launch(NumberGetter numberGetter, SumCalculator sumCalculator) {
        try {
            NumberManager numberManager = new NumberManager(numberGetter);
            int number = numberManager.getNumericValue();
            logger.info("Retrieved number: {}", number);
            logger.info("Sum of numbers from 1 to {}: {}", number, sumCalculator.sum(number));
        } catch (IllegalStateException | IllegalArgumentException e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
    }
}