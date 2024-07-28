package cy.markelova.doroti.entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Robot {

    public List<String> getNameFiles(File file) {
        List<String> names = new ArrayList<>();

        try (FileReader fileReader = new FileReader(file);
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
            throw new RuntimeException(e);
        }
        return names;
    }
}
