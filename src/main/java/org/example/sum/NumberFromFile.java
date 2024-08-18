package org.example.sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
            logger.error("Error creating file at {}", filePath, e);
            throw new RuntimeException("Error creating file", e);
        }
    }

    @Override
    public int get() {
        return readFirstLineInt().orElseGet(() -> {
            logger.info("File {} is empty or doesn't contain a valid number", filePath);
            return 0;
        });
    }

    private Optional<Integer> readFirstLineInt(){
        try {
            return Files.lines(filePath)
                    .findFirst()
                    .map(String::trim)
                    .map(this::parseIntOrLog);
        } catch (IOException e){
            logger.error("Error reading the number from file {}", filePath, e);
            throw new RuntimeException("Error reading the number from file", e);
        }
    }

    private Integer parseIntOrLog(String line){
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e){
            logger.warn("First line in file {} is not a valid integer: {}", filePath, line);
            return null;
        }
    }
}
