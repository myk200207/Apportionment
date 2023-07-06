package edu.virginia.cs.hw3;

public class StateReaderFactory {
    /**
     * This class is responsible for giving the user the correct StateReader for their input filename.
     */
        /**
         * Factory method - takes in a file and returns the corresponding StateReader for that file
         * @param filename - the name of the input file, such as "states.csv" or "States.xlsx"
         * @return a StateReader object
         */
        public StateReader getStateReader(String filename) {
            if (filename.endsWith("csv")) {
                return new CSVStateReader(filename);
            } else if (filename.endsWith("xlsx")) {
                return new ExcelStateReader(filename);
            } else {
                throw new IllegalArgumentException("Please input a CSV or XLSX file. ERROR: Invalid File Type");
            }
        }
    }
