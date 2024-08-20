package org.example.sum;

import org.example.number.NumberFromFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

class NumberFromFileTest {
    private NumberFromFile numberFromFile;
    private Path tempFile;
    private String originalContent;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("testFile", ".txt");
        Files.writeString(tempFile, "42");
        numberFromFile = NumberFromFile.getInstance(tempFile.toString());
        originalContent = Files.readString(tempFile);

        Field instanceField = NumberFromFile.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.writeString(tempFile, originalContent);
        Files.deleteIfExists(tempFile);

        /* Have to reset the singleton instance after each test (for safety).
        The getInstance method returns a single instance of the class,
        and this instance is shared across all tests in the same JVM session.
        If one test modifies the file content or state, that change could affect subsequent tests,
        leading to unexpected behavior and test failures when all tests are run together.
         */
        Field instanceField = NumberFromFile.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }

    @Test
    void testSingletonInstance() {
        NumberFromFile instance1 = NumberFromFile.getInstance(tempFile.toString());
        NumberFromFile instance2 = NumberFromFile.getInstance(tempFile.toString());
        assertSame(instance1, instance2, "Expected the same instance of NumberFromFile for both calls");
    }

    @Test
    void testGetReturnsCorrectValue() {
        int result = numberFromFile.get();
        assertEquals(42, result, "Expected get() to return 42 from the file");
    }

    @Test
    void testParseIntOrLogHandlesInvalidInput() throws Exception {
        Files.writeString(tempFile, "invalid");

        Method readFirstLineIntMethod = NumberFromFile.class.getDeclaredMethod("readFirstLineInt");
        readFirstLineIntMethod.setAccessible(true);
        Optional<Integer> result = (Optional<Integer>) readFirstLineIntMethod.invoke(numberFromFile);

        assertTrue(result.isEmpty(), "Expected result to be empty for invalid integer input");
    }

    @Test
    void testReadFirstLineIntLogsErrorOnIOException() throws Exception {
        Constructor<NumberFromFile> constructor = NumberFromFile.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        numberFromFile = constructor.newInstance(tempFile.toString());

        try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
            filesMock.when(() -> Files.lines(any(Path.class))).thenThrow(new IOException("IO Error"));

            Method readFirstLineIntMethod = NumberFromFile.class.getDeclaredMethod("readFirstLineInt");
            readFirstLineIntMethod.setAccessible(true);
            Optional<Integer> result = (Optional<Integer>) readFirstLineIntMethod.invoke(numberFromFile);
            assertTrue(result.isEmpty(), "Expected result to be empty when IOException occurs");
        }
    }

    @Test
    void testGetThrowsExceptionOnEmptyFile() throws IOException {
        Files.writeString(tempFile, "");

        assertThrows(IllegalStateException.class, () -> {
            numberFromFile.get();
        }, "Expected IllegalStateException when the file is empty");
    }

    @Test
    void testGetThrowsExceptionOnNonNumericContent() throws IOException {
        Files.writeString(tempFile, "NotANumber");

        assertThrows(IllegalStateException.class, () -> {
            numberFromFile.get();
        }, "Expected IllegalStateException for non-numeric file content");
    }

    @Test
    void testGetReturnsFirstLineValue() throws IOException {
        Files.writeString(tempFile, "42\n123\n456");

        int result = numberFromFile.get();
        assertEquals(42, result, "Expected get() to return the first line value of 42");
    }

    @Test
    void testGetHandlesLargeNumbers() throws IOException {
        Files.writeString(tempFile, "65535");

        int result = numberFromFile.get();
        assertEquals(65535, result, "Expected get() to return the maximum value 65535");
    }

    @Test
    void testGetHandlesWhitespace() throws IOException {
        Files.writeString(tempFile, "    42    ");

        int result = numberFromFile.get();
        assertEquals(42, result, "Expected get() to return 42 even when surrounded by whitespace");
    }

    @Test
    void testFileCreationIfNotExists() throws IOException {
        Path nonExistentFile = tempFile.getParent().resolve("nonExistentFile.txt");

        assertFalse(Files.exists(nonExistentFile), "Expected the file to not exist before the instance is created");
        NumberFromFile.getInstance(nonExistentFile.toString());
        assertTrue(Files.exists(nonExistentFile), "Expected the file to be created if it does not exist");

        Files.deleteIfExists(nonExistentFile);
    }

    @Test
    void testCreateFileIfNotExistsThrowsExceptionOnIOException() throws Exception {
        Constructor<NumberFromFile> constructor = NumberFromFile.class.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);

        try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
            filesMock.when(() -> Files.notExists(any(Path.class))).thenReturn(true);
            filesMock.when(() -> Files.createFile(any(Path.class))).thenThrow(new IOException("Test IO Exception"));

            InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {
                constructor.newInstance("somePath.txt");
            }, "Expected InvocationTargetException due to reflection when IOException occurs");

            Throwable cause = exception.getCause();
            assertInstanceOf(IllegalStateException.class, cause, "Expected cause of InvocationTargetException to be IllegalStateException");
        }
    }
}