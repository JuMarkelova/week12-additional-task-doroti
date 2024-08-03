package doroti;

import cy.markelova.doroti.entity.Robot;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RobotTests {

    @BeforeClass
    public void createFile() {
        File file = new File("test.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            String s1 = "Hi, my name is dehdeh2*,. and yours?";
            String s2 = "I want to tell you 6hDHe! That is.";
            fileWriter.write(s1 + "\n" + s2);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @AfterTest
    public void deleteFile() {
        File testFile = new File("test.txt");
        File testFileFirst = new File("first.txt");
        File testFileSecond = new File("second.txt");
        File testFileThird = new File("third.txt");
        File testDirDate = new File("data");
        testFile.delete();
        testFileFirst.delete();
        testFileSecond.delete();
        testFileThird.delete();
        testDirDate.delete();
    }

    @Test
    public void testNamesForNewFiles() {
        Robot robot = new Robot("test.txt");
        List<String> names = robot.getNameFiles();
        Assert.assertEquals(names.getFirst(), "and", "Wrong name 1");
        Assert.assertEquals(names.get(1), "That", "Wrong name 1");
    }

    @Test
    public void createFullWordList() {
        Robot robot = new Robot("test.txt");
        List<String> words = robot.getDistinctWords();
        Assert.assertEquals(words.size(), 14, "Wrong number of words");
        Assert.assertEquals(words.getFirst(), "Hi", "Wrong first word");
        Assert.assertEquals(words.get(4), "dehdeh2*", "Wrong fifth word");
    }

    @Test
    public void checkEvenWords() {
        Robot robot = new Robot("test.txt");
        List<String> evenWords = robot.getEvenWords();
        Assert.assertEquals(evenWords.size(), 2, "Wrong number of even words");
    }

    @Test
    public void checkOddWords() {
        Robot robot = new Robot("test.txt");
        List<String> oddWords = robot.getOddWords();
        Assert.assertEquals(oddWords.size(), 12, "Wrong number of odd words");
    }

    @Test
    public void createFirstFile() {
        Robot robot = new Robot("test.txt");
        robot.createFirstFile("first.txt");
        File file = new File("first.txt");
        robot.setOriginFile(file);
        List<String> evenWords = robot.getDistinctWords();
        Assert.assertEquals(evenWords.size(), 2, "Wrong number of even words");
    }

    @Test
    public void createSecondFile() {
        Robot robot = new Robot("test.txt");
        robot.createSecondFile("second.txt");
        File file = new File("second.txt");
        robot.setOriginFile(file);
        List<String> evenWords = robot.getDistinctWords();
        Assert.assertEquals(evenWords.size(), 12, "Wrong number of odd words");
    }

    @Test
    public void defineWrongWords() {
        Robot robot = new Robot("test.txt");
        robot.getNameFiles();
        Assert.assertEquals(robot.getNotWordsList().size(), 4, "Wrong number of wrong words");
        Assert.assertEquals(robot.getNotWordsList().getFirst(), "dehdeh2*", "Incorrect definition of not a word");
        Assert.assertEquals(robot.getNotWordsList().get(1), "and", "Incorrect definition of not a word");
        Assert.assertEquals(robot.getNotWordsList().get(2), "6hDHe", "Incorrect definition of not a word");
        Assert.assertEquals(robot.getNotWordsList().get(3), "That", "Incorrect definition of not a word");
    }

    @Test
    public void countSymbols() {
        Robot robot = new Robot("test.txt");
        int countDots = robot.countSymbol('.');
        int countSemicolons = robot.countSymbol(';');
        Assert.assertEquals(countDots, 2, "Wrong number of dots");
        Assert.assertEquals(countSemicolons, 0, "Wrong number of semicolons");
    }

    @Test
    public void createThirdFile() {
        Robot robot = new Robot("test.txt");
        robot.createThirdFile("third.txt");
        int countCommas = robot.countSymbol(',');
        int countQuestions = robot.countSymbol('?');
        Assert.assertEquals(countCommas, 2, "Wrong number of commas");
        Assert.assertEquals(countQuestions, 1, "Wrong number of question signs");
    }

    @Test
    public void createDataDir() {
        Robot robot = new Robot("test.txt");
        File file = new File("data");
        robot.createDirData();
        Assert.assertTrue(file.isDirectory(), "Data is not a directory");
    }
}