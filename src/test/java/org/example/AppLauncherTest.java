package org.example;

import org.example.sum.NumberGetter;
import org.example.sum.SumCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppLauncherTest {

    @Test
    void testLaunchWithValidInput() {
        NumberGetter mockNumberGetter = mock(NumberGetter.class);
        SumCalculator mockSumCalculator = mock(SumCalculator.class);

        when(mockNumberGetter.get()).thenReturn(5);
        when(mockSumCalculator.sum(5)).thenReturn(15);

        AppLauncher appLauncher = new AppLauncher();

        appLauncher.launch(mockNumberGetter, mockSumCalculator);

        verify(mockNumberGetter).get();
        verify(mockSumCalculator).sum(5);
    }

    @Test
    void testMultipleLaunches() {
        NumberGetter mockNumberGetter = mock(NumberGetter.class);
        SumCalculator mockSumCalculator = mock(SumCalculator.class);

        when(mockNumberGetter.get()).thenReturn(5, 10, 15);
        when(mockSumCalculator.sum(anyInt())).thenAnswer(invocation -> {
            int arg = invocation.getArgument(0);
            return (arg * (arg + 1)) / 2;
        });

        AppLauncher appLauncher = new AppLauncher();

        appLauncher.launch(mockNumberGetter, mockSumCalculator);
        appLauncher.launch(mockNumberGetter, mockSumCalculator);
        appLauncher.launch(mockNumberGetter, mockSumCalculator);

        verify(mockNumberGetter, times(3)).get();
        verify(mockSumCalculator).sum(5);
        verify(mockSumCalculator).sum(10);
        verify(mockSumCalculator).sum(15);
    }

    @Test
    void testLaunchWithNumberGetterException() {
        NumberGetter mockNumberGetter = mock(NumberGetter.class);
        SumCalculator mockSumCalculator = mock(SumCalculator.class);

        when(mockNumberGetter.get()).thenThrow(new IllegalStateException("Invalid state"));

        AppLauncher appLauncher = new AppLauncher();

        appLauncher.launch(mockNumberGetter, mockSumCalculator);

        verify(mockNumberGetter).get();
        verify(mockSumCalculator, never()).sum(anyInt());
    }

    @Test
    void testLaunchWithSumCalculatorException() {
        NumberGetter mockNumberGetter = mock(NumberGetter.class);
        SumCalculator mockSumCalculator = mock(SumCalculator.class);

        when(mockNumberGetter.get()).thenReturn(5);
        when(mockSumCalculator.sum(5)).thenThrow(new IllegalArgumentException("Invalid argument"));

        AppLauncher appLauncher = new AppLauncher();

        appLauncher.launch(mockNumberGetter, mockSumCalculator);

        verify(mockNumberGetter).get();
        verify(mockSumCalculator).sum(5);
    }
}