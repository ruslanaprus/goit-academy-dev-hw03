package org.example.sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class NumberFromFile implements NumberGetter {
    private static final Logger logger = LoggerFactory.getLogger(NumberFromFile.class);
    private static NumberFromFile instance;
    private final Path filePath;

    private NumberFromFile(String filePath) {
        this.filePath = Paths.get(filePath);
        createFileIfNotExists();
    }

    public static NumberFromFile getInstance(String filePath) {
        if (instance == null) {
            instance = new NumberFromFile(filePath);
        }
        return instance;
    }

    private void createFileIfNotExists() {
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                logger.info("Created new file at {}", filePath);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error creating file at " + filePath, e);
        }
    }

    @Override
    public int get() {
        return readFirstLineInt().orElseThrow(() -> {
            String message = String.format("File %s is empty or doesn't contain a valid number", filePath);
            logger.error(message);
            return new IllegalStateException(message);
        });
    }

    private Optional<Integer> readFirstLineInt(){
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines
                    .findFirst()
                    .map(String::trim)
                    .map(this::parseIntOrLog);
        } catch (IOException e){
            logger.error("Error reading the file {}: {}", filePath, e.getMessage(), e);
            return Optional.empty();
        }
    }

    private Integer parseIntOrLog(String line){
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e){
            logger.warn("Cannot parse the line as integer from file {}: line content: '{}'", filePath, line);
            return null;
        }
    }
}
