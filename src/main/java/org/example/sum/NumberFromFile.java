package org.example.sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class NumberFromFile implements NumberGetter {
    private static final Logger logger = LoggerFactory.getLogger(NumberFromFile.class);
    private static NumberFromFile instance;
    private String filePath;

    private NumberFromFile(String filePath) {
        this.filePath = filePath;
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
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
                logger.info("Created new file at {}", filePath);
            }
        } catch (IOException e) {
            logger.error("Error creating file at {}", filePath, e);
            throw new RuntimeException("Error creating file", e);
        }
    }

    @Override
    public int get() {
        try {
            Path path = Paths.get(filePath);
            List<String> fileContent = Files.readAllLines(path);

            if (!fileContent.isEmpty()) {
                String firstLine = fileContent.get(0).trim();
                try {
                    return Integer.parseInt(firstLine);
                } catch (NumberFormatException e) {
                    logger.warn("First line in file {} is not a valid integer: {}", filePath, firstLine);
                }
            }
            logger.info("File {} is empty or doesn't contain a valid number", filePath);
            return 1;
        } catch (IOException e) {
            logger.error("Error reading the number from file {}", filePath, e);
            throw new RuntimeException("Error reading the number from file");
        }
    }
}
