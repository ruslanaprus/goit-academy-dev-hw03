package org.example;

import org.example.sum.*;
import org.example.sum.NumberGetter;

public class AppLauncher {
    public static void main(String[] args) {
//        NumberGetter userInput = new UserInput();
//        NumberManager numberManager = new NumberManager(userInput);
//        NumberGetter randomInt = new RandomInt();
//        NumberManager numberManager = new NumberManager(randomInt);
        NumberGetter numberFromFile = NumberFromFile.getInstance("src/main/resources/number.txt");
        NumberManager numberManager = new NumberManager(numberFromFile);
        int number = numberManager.getNumericValue();
        System.out.println("number: " + number);
        SumCalculator sumCalculator = new SumCalculator();
        System.out.println("Sum of numbers: " + sumCalculator.sum(number));
    }
}