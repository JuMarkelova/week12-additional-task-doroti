package cy.markelova.doroti._main;

import cy.markelova.doroti.entity.Robot;

import java.util.List;

public class _Main {

    public static void main(String[] args) {
        String path = "/Users/iuliia/javaStudy/ITGirls/Module1JavaCore/Week12/week12-additional-task-doroti/src/main/resources/text.txt";
        Robot doroti = new Robot(path);
        List<String> fileNames = doroti.getNameFiles();
        String directoryPath = doroti.createDirData();

        doroti.createFirstFile(directoryPath, fileNames.getFirst());
        doroti.createSecondFile(directoryPath, fileNames.get(1));
        doroti.createThirdFile(directoryPath, "punctuation");
    }
}