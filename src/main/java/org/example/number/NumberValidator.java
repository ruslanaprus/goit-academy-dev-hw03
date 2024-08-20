package org.example.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.constants.Constants.MAX_VALUE;
import static org.example.constants.Constants.MIN_VALUE;

public class NumberValidator {
    private static final Logger logger = LoggerFactory.getLogger(NumberValidator.class);

    public boolean isValidNumber(int number) {
        if (number >= MIN_VALUE && number <= MAX_VALUE) {
            return true;
        } else {
            logger.warn("Number out of range. Number should be between {} and {}", MIN_VALUE, MAX_VALUE);
            return false;
        }
    }
}
