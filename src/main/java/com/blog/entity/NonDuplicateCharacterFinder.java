package com.blog.entity;

import java.util.HashMap;
import java.util.Map;

public class NonDuplicateCharacterFinder {

    public static char findFirstNonDuplicateCharacter(String str) {
        // Create a HashMap to store the frequency of each character in the string
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        // Traverse the string and populate the charFrequencyMap
        for (char ch : str.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Traverse the string again to find the first non-duplicate character
        for (char ch : str.toCharArray()) {
            if (charFrequencyMap.get(ch) == 1) {
                return ch;
            }
        }

        // If no non-duplicate character found, return a default value (you can handle this according to your requirements)
        return '\0';
    }

    public static void main(String[] args) {
        String inputString = "apple";
        char result = findFirstNonDuplicateCharacter(inputString);
        if (result != '\0') {
            System.out.println("First non-duplicate character: " + result);
        } else {
            System.out.println("No non-duplicate character found.");
        }
    }
}

