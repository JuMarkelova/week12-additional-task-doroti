package doroti;

import cy.markelova.doroti.entity.Robot;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RobotTests {

    @Test
    public void testNamesForNewFiles() {
        File file = new File("test.txt");
        Robot robot = new Robot();
        try (FileWriter fileWriter = new FileWriter(file)) {
            String s1 = "Hi, my name is dehdeh2*, and yours?";
            String s2 = "I want to tell you 6hDHe! That";
            fileWriter.write(s1 + "\n" + s2);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        List<String> names = robot.getNameFiles(file);
        Assert.assertEquals(names.getFirst(), "and", "Wrong name 1");
        Assert.assertEquals(names.get(1), "That", "Wrong name 1");
    }

    @AfterTest
    public void deleteFile() {
        File file = new File("test.txt");
        file.delete();
    }
}