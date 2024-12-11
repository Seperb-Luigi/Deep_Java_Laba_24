package com.volkov.lab24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FilterStrings {
    public static Map<String, Integer> filterAndCalculateFrequencies(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));

        String[] words = content.toLowerCase()
                .replaceAll("[^a-zA-Zа-яА-Я0-9\\s]", "")
                .split("\\s+");

        Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty() || word.matches("\\d+")) { // Пропускаємо порожні рядки та числа
                continue;
            }
            wordFrequencies.merge(word, 1, Integer::sum);
        }
        return wordFrequencies;
    }
}

