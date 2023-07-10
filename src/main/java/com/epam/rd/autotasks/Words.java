package com.epam.rd.autotasks;

import java.util.*;

public class Words {
    public static String countWords(List<String> lines) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String line : lines) {
            String[] words = line.split("[^\\p{L}\\p{Digit}]+"); // Split line into words

            for (String word : words) {
                if (word.length() < 4) {
                    continue;
                }

                String normalizedWord = word.toLowerCase();

                int count = wordCount.getOrDefault(normalizedWord, 0);
                wordCount.put(normalizedWord, count + 1);
            }
        }

        Iterator<Map.Entry<String, Integer>> iterator = wordCount.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 10) {
                iterator.remove();
            }
        }

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

        StringBuilder statistics = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            statistics.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }

        return statistics.toString().trim();
    }
}
