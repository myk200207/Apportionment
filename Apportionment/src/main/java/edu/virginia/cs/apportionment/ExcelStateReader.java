package edu.virginia.cs.hw3;

import org.apache.poi.ss.formula.functions.Intercept;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ExcelStateReader extends StateReader {
    private String filename;
    public Workbook workbook = null;

    private final int NAME_COLUMN_INDEX = 0 ;
    private final int POP_COLUMN_INDEX = 1;
    public ExcelStateReader(String filename) {
        if (!filename.toLowerCase().endsWith(".xlsx")) {
            throw new IllegalArgumentException("Error: cannot open non excel file " + filename);
        }

        this.filename = filename;
        //System.out.println(filename);
    }

    @Override
    public void readStates() {
        try {
            generateBufferedReader();
            getStatesFromRestOfFile();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO: unimplemented stub
    }

    private void generateBufferedReader() throws FileNotFoundException {

        try {
            //System.out.println(filename);
            FileInputStream fis = new FileInputStream(new File(filename));
            this.workbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IOException");
            System.exit(0);
        }
    }

    private void getStatesFromRestOfFile() throws IOException {
        Sheet mysheet = workbook.getSheetAt(0);

        Iterator<Row> rowIter = mysheet.iterator();
        rowIter.next();
        //System.out.println(g.getCell(0));
        while (rowIter.hasNext()) {
            Row r = rowIter.next();
            Iterator<Cell> cellIterator = r.cellIterator();
            try {
                addstateFromLine(r);
            } catch (IndexOutOfBoundsException | IllegalArgumentException ignored) {
            }

        }
    }

    private void addstateFromLine(Row r) {
        //System.out.println(r.getCell(NAME_COLUMN_INDEX).toString().strip().matches("[A-Za-z]+ ?[A-Za-z]+?"));
//      System.out.println(r.getCell(POP_COLUMN_INDEX).toString().matches("[0-9.]+"));
//        System.out.println(r.getCell(NAME_COLUMN_INDEX));
//        System.out.println(r.getCell(POP_COLUMN_INDEX));
//        System.out.println(r.getCell(NAME_COLUMN_INDEX) != null);
//        System.out.println(r.getCell(POP_COLUMN_INDEX) != null);
        if(r.getCell(NAME_COLUMN_INDEX) != null
                && r.getCell(NAME_COLUMN_INDEX).toString().strip().matches("[A-Za-z ]+")
                && r.getCell(POP_COLUMN_INDEX) != null
                && r.getCell(POP_COLUMN_INDEX).toString().matches("([0-9]+).?([0-9]+)?E?([0-9+]+)?")) {
            State newState = new State(r.getCell(NAME_COLUMN_INDEX).toString().strip(), (int) r.getCell(POP_COLUMN_INDEX).getNumericCellValue());
            getStates();
            stateList.add(newState);
            //System.out.println(newState.getName() + " - " + newState.getPopulation());
        }

    }
}

