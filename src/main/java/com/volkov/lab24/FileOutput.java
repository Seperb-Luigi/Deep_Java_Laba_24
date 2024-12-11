package com.volkov.lab24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileOutput {
    private static final String DEFAULT_FILE_PATH = "src/main/resources/test.txt";

    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : DEFAULT_FILE_PATH;

        try {
            if (!Files.exists(Path.of(filePath))) {
                System.err.println("File not found at path: " + filePath);
                return;
            }

            Map<String, Integer> wordFrequencies = FilterStrings.filterAndCalculateFrequencies(filePath);

            if (wordFrequencies.isEmpty()) {
                System.out.println("No valid words found in the file.");
            } else {
                System.out.println("Word frequencies (filtered numbers):");
                wordFrequencies.forEach((word, frequency) -> System.out.printf("%s: %d%n", word, frequency));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

