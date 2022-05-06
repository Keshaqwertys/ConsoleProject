package Program;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class CutTest {
    @Test
    public void getSlice() throws IOException {
        Cut cutter;
        File outputFile;

        // Тест 1
        File testFile1 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test1.txt");

        cutter = new Cut(false,true, "outputFile.txt","inputFile.txt","0-11",false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile1));


        // Тест 2
        File testFile2 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test2.txt");

        cutter = new Cut(false,true, "outputFile.txt","inputFile.txt","1-2",false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile2));

        // Тест 3

        File testFile3 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test3.txt");

        cutter = new Cut(false,true, "outputFile.txt","inputFile.txt","1-",false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile3));

        // Тест 4

        File testFile4 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test4.txt");

        cutter = new Cut(false,true,"outputFile.txt", "inputFile.txt", "-0", false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile4));

        // Тест 5

        File testFile5 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test5.txt");

        cutter = new Cut(true,false,"outputFile.txt", "inputFile.txt", "0-50", false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile5));


        // Тест 6

        File testFile6 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test6.txt");

        cutter = new Cut(true,false,"outputFile.txt", "inputFile.txt", "2-", false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile6));

        // Тест 7

        File testFile7 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test7.txt");

        cutter = new Cut(true,false,"outputFile.txt", "inputFile.txt", "-10", false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile7));

        // Тест 8

        File testFile8 = new  File("C:\\Users\\Иннокентий\\IdeaProjects\\ConsoleProject\\src\\main\\resources\\Test8.txt");

        cutter = new Cut(true,false,"outputFile.txt", "inputFile.txt", "100-200", false);
        cutter.getSlice();

        outputFile = cutter.getOutputFile();

        assertTrue(cutter.assertEqualsFile(outputFile,testFile8));
    }
}
