package cy.markelova.doroti.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Robot {

    private File originFile;

    public Robot(String path) {
        this.originFile = new File(path);
    }

    public File getOriginFile() {
        return originFile;
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
                        } else if (wrongWord != null) {
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
                    words.add(word);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }
}
