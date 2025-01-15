package pij.main;

import java.io.*;
import java.util.*;


public class Dictionary {
    private Set<String> words;
    public Dictionary() {

        words = new HashSet<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader("resources/wordlist.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isValidWords(String word) {
        return words.contains(word);
    }
}

