package edu.virginia.cs.hw3;

import org.junit.jupiter.api.Test;

public class ExcelStateReaderTester {

    @Test
    public void ExcelStateReaderEquivalenceTest(){
        //String path = "/Users/michaelkim/IdeaProjects/hw3-yk4vs-vuc7cu-csp8fy/src/test/java/edu.virginia.cs.hw3/census2020.xlsx";
        //String path2 = "/Users/michaelkim/IdeaProjects/hw3-yk4vs-vuc7cu-csp8fy/src/test/java/edu.virginia.cs.hw3/census2020.csv";
        String path = "C:/Users/Joshua Seiden/SDE/hw3-yk4vs-vuc7cu-csp8fy/src/test/java/edu/virginia/cs/hw3/1990census.xlsx";
        ExcelStateReader Readingfile = new ExcelStateReader(path);
        //CSVStateReader Readingfile = new CSVStateReader(path2);
        Readingfile.readStates();
    }
    @Test
    public void ExcelStateReaderBadRowsTest(){
        String path = "C:/Users/Joshua Seiden/SDE/hw3-yk4vs-vuc7cu-csp8fy/src/test/java/edu/virginia/cs/hw3/badformat5statetest.xlsx";
        ExcelStateReader Readingfile = new ExcelStateReader(path);
        Readingfile.readStates();
    }
    @Test
    public void NoDataExcelFileTest(){
        String path = "C:/Users/Joshua Seiden/SDE/hw3-yk4vs-vuc7cu-csp8fy/src/test/java/edu/virginia/cs/hw3/emptyexcelfile.xlsx";
        ExcelStateReader Readingfile = new ExcelStateReader(path);
        Readingfile.readStates();
    }
}
