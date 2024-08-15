package org.example;

public class AppLauncher {
    public static void main(String[] args) {
        SumCalculator sumCalculator = new SumCalculator();
        System.out.println("Sum of numbers: " + sumCalculator.sum(7));
    }
}