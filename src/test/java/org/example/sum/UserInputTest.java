package org.example.sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        userInput = new UserInput();
    }

    @Test
    public void testValidInput() {
        String simulatedInput = "7\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(7, result, "The method should return the valid positive integer input");
    }

    @Test
    public void testInvalidThenValidInput() {
        String simulatedInput = "meow\n-11\n0\n7\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(7, result, "The method should skip invalid inputs and continue asking for input until a valid positive integer is provided");
    }

    @Test
    public void testMinimumValidInput(){
        String simulatedInput = "1\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(1, result, "The method should accept the smallest positive integer input (1)");

    }

}