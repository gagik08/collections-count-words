package com.epam.rd.autotasks;

import java.util.*;

public class Words {
    public static String countWords(List<String> lines) {
        Map<String, Integer> wordCount = new HashMap<>();

        // Iterate over each line of text
        for (String line : lines) {
            String[] words = line.split("[^\\p{L}\\p{Digit}]+"); // Split line into words

            // Iterate over each word
            for (String word : words) {
                // Ignore words with less than 4 characters
                if (word.length() < 4) {
                    continue;
                }

                // Normalize the word to lowercase
                String normalizedWord = word.toLowerCase();

                // Count the occurrence of each word
                int count = wordCount.getOrDefault(normalizedWord, 0);
                wordCount.put(normalizedWord, count + 1);
            }
        }

        // Filter out words that appear less than 10 times
        Iterator<Map.Entry<String, Integer>> iterator = wordCount.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) {
                iterator.remove();
            }
        }

        // Sort the entries by amount and then alphabetically
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCount.entrySet());
        sortedEntries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                int countComparison = Integer.compare(entry2.getValue(), entry1.getValue());
                if (countComparison != 0) {
                    return countComparison;
                }
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });

        // Build the statistics string
        StringBuilder statistics = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            statistics.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }

        // Trim the trailing whitespace
        String result = statistics.toString().trim();

        return result;
    }
}
