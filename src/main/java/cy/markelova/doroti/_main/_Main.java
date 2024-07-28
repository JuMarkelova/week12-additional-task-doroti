package cy.markelova.doroti._main;

import cy.markelova.doroti.entity.Robot;

import java.io.File;
import java.util.List;

public class _Main {

    public static void main(String[] args) {
        File originFile = new File("/Users/iuliia/javaStudy/ITGirls/Module1JavaCore/Week12/week12-additional-task-doroti/src/main/resources/text.txt");
        Robot doroti = new Robot();

        List<String> fileNames = doroti.getNameFiles(originFile);

        for (String el : fileNames) {
            System.out.println(el);
        }
    }
}