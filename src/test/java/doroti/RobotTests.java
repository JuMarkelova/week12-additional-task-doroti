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
            String s1 = "Hi, my name is dehdeh2*, and yours?";
            String s2 = "I want to tell you 6hDHe! That";
            fileWriter.write(s1 + "\n" + s2);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @AfterTest
    public void deleteFile() {
        File file = new File("test.txt");
        file.delete();
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
        List<String> words = robot.getWords();
        Assert.assertEquals(words.size(), 14, "Wrong number of words");
        Assert.assertEquals(words.getFirst(), "Hi", "Wrong first word");
        Assert.assertEquals(words.get(4), "dehdeh2*", "Wrong fifth word");
    }
}