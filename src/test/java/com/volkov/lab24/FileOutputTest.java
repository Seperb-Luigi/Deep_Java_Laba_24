package com.volkov.lab24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FileOutputTest {

    @Test
    void testFiltAndCalcFreq_NoValidWords() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "12345 6789 101112");

        Map<String, Integer> frequencies = FilterStrings.filterAndCalculateFrequencies(tempFile.toString());

        assertEquals(0, frequencies.size());

        Files.delete(tempFile);
    }

    @Test
    void testFiltAndCalcFreq_SpecialCharacters() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "S@alam va@aleikum  ^&*%  $%^$%  (*$%%^4 76 ");

        Map<String, Integer> frequencies = FilterStrings.filterAndCalculateFrequencies(tempFile.toString());

        assertEquals(1, frequencies.get("Salam"));
        assertEquals(1, frequencies.get("vaaleikum"));
        assertEquals(1, frequencies.get("test123"));
        assertNull(frequencies.get("S@alam"));

        Files.delete(tempFile);
    }


    @Test
    void testFiltAndCalcFreq_ValidInput() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "777 shalom world gitatra gitatra gitatra  shalom world");

        Map<String, Integer> frequencies = FilterStrings.filterAndCalculateFrequencies(tempFile.toString());

        assertNull(frequencies.get("777"));
        assertEquals(2, frequencies.get("shalom"));
        assertEquals(2, frequencies.get("world"));
        assertEquals(3, frequencies.get("gitatra"));

        Files.delete(tempFile);
    }

    @Test
    void testFiltAndCalcFreq_EmptyFile() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "");

        Map<String, Integer> frequencies = FilterStrings.filterAndCalculateFrequencies(tempFile.toString());

        assertEquals(0, frequencies.size());

        Files.delete(tempFile);
    }



}
