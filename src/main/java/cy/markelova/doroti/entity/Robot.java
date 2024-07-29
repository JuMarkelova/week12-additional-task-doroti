package cy.markelova.doroti.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Robot {

    private File originFile;
    List<String> notWordsList = new ArrayList<>();

    public Robot(String path) {
        this.originFile = new File(path);
    }

    public File getOriginFile() {
        return originFile;
    }

    public void setOriginFile(File originFile) {
        this.originFile = originFile;
    }

    public List<String> getNotWordsList() {
        return notWordsList;
    }

    public void setNotWordsList(List<String> notWordsList) {
        this.notWordsList = notWordsList;
    }

    public List<String> getNameFiles() {
        List<String> names = new ArrayList<>();

        try (FileReader fileReader = new FileReader(this.originFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String wrongWord = null;
            String line;
            while (names.size() < 2) {
                while ((line = bufferedReader.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line);

                    while (tokenizer.hasMoreTokens()) {
                        String word = tokenizer.nextToken();
                        word = word.replaceAll("^[.,]?|[.,!?;:]+$", "");
                        if (!word.matches("[a-zA-Z]+-*[a-zA-Z]*")) {
                            wrongWord = word;
                            notWordsList.add(wrongWord);
                        } else if (wrongWord != null) {
                            notWordsList.add(word);
                            names.add(word);
                            wrongWord = null;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return names;
    }

    public List<String> getWords() {
        List<String> words = new ArrayList<>();
        String word;
        try (FileReader reader = new FileReader(this.originFile);
             BufferedReader bReader = new BufferedReader(reader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);

                while (tokenizer.hasMoreTokens()) {
                    word = tokenizer.nextToken();
                    word = word.replaceAll("^[.,]?|[.,!?;:]+$", "");
                    if (!notWordsList.contains(word)) {
                        words.add(word);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }

    public List<String> getEvenWords() {
        List<String> words = getWords();
        List<String> evenWords = new ArrayList<>();
        for (String el : words) {
            int countEvenSymbol = countVowels(el);
            if (countEvenSymbol % 2 == 0) {
                evenWords.add(el);
            }
        }
        return evenWords;
    }

    public List<String> getOddWords() {
        List<String> words = getWords();
        List<String> evenWords = new ArrayList<>();
        for (String el : words) {
            if (countVowels(el) % 2 == 1) {
                evenWords.add(el);
            }
        }
        return evenWords;
    }

    private int countVowels(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    private boolean isVowel(char symbol) {
        String vowels = "[aeyuioAEYUIO]";
        return String.valueOf(symbol).matches(vowels);
    }

    public void createFirstFile(String name) {
        File file = new File(name);
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            List<String> words = getEvenWords();
            for (String word : words) {
                bufferedWriter.write(word + " ");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void createSecondFile(String name) {
        File file = new File(name);
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            List<String> words = getOddWords();
            for (String word : words) {
                bufferedWriter.write(word + " ");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
