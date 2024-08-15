package org.example;

import org.example.sum.Number;
import org.example.sum.NumberService;
import org.example.sum.SumCalculator;
import org.example.sum.UserInput;

public class AppLauncher {
    public static void main(String[] args) {
        Number userInput = new UserInput();
        NumberService numberService = new NumberService(userInput);
        int n = numberService.getNumber();
        SumCalculator sumCalculator = new SumCalculator();
        System.out.println("Sum of numbers: " + sumCalculator.sum(n));
    }
}