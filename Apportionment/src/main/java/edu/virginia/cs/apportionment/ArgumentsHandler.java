package edu.virginia.cs.hw3;

import java.util.*;
import java.io.*;

public class ArgumentsHandler {

    public static final int FILENAME_INDEX = 0;
    public static final int REPRESENTATIVES_INDEX = 1;
    private final List<String> arguments;
    public int reps = -1;
    private int counter = 0;
    public String format = "";
    public String algorithm = "";


    private Configuration config;
    public ArgumentsHandler argumentsHandler;

    public ArgumentsHandler(List<String> arguments) {
        if (arguments.size() < 1) {
            throw new IllegalArgumentException("Error: No arguments were included at runtime. Arguments expected\n" +
                    "statePopulationFilename [number of representatives] [--hamilton]");
        }
        this.arguments = arguments;
        //System.out.println("it uses this for size");
    }

    public ArgumentsHandler(String[] args) {

        this(Arrays.asList(args));
       // System.out.println((Arrays.asList(args)));
        //System.out.println("it uses this");
    }

    public Configuration getConfiguration() {
        this.config = new Configuration();
        //System.out.println("works");
        if(arguments.size()==1) {
            setDefaultConfiguration();
            configureStateReader();
            checkForRepresentativeCount();
            return config;
        }
        configureStateReader();

//        if(arguments.get(1)equals("-") && arguments.get(1)[1].equals("-")
//        arguments.get(1).contains("bcdeghijklmnopqstuvwxyz")){
//            throw new IllegalArgumentException("Invalid")
//        }

        try {
            for (int i = 0; i < arguments.size(); i++) {


                if (arguments.get(i).matches("[A-Za-z]+")) {
                    arguments.get(i).toLowerCase();
                }

                //System.out.println("INSIDE FOR LOOP " + arguments.get(i));

                if ((arguments.get(i).equals("-r") || arguments.get(i).equals("--reps"))
                        && arguments.get(i + 1).matches("[0-9]+")) {

                    this.reps = (Integer.parseInt(arguments.get(i + 1)));
                } else if ((arguments.get(i).equals("-f") || arguments.get(i).equals("--format"))
                        && (arguments.get(i + 1).equals("benefit") || arguments.get(i + 1).equals("alpha"))) {
                    this.format = arguments.get(i + 1);
                } else if ((arguments.get(i).equals("-a") || arguments.get(i).equals("--algorithm"))
                        && (arguments.get(i + 1).equals("hamilton") || arguments.get(i + 1).equals("jefferson") ||
                        arguments.get(i + 1).equals("huntington"))) {
                    this.algorithm = arguments.get(i + 1);
                } else if ((arguments.get(i).equals("-rf")) && arguments.get(i + 1).matches("[0-9]+")
                        && (arguments.get(i + 2).equals("benefit") || arguments.get(i + 2).equals("alpha"))) {
                    this.format = arguments.get(i + 2);
                    this.reps = (Integer.parseInt(arguments.get(i + 1)));
                } else if ((arguments.get(i).equals("-ra"))
                        && arguments.get(i + 1).matches("[0-9]+") && (arguments.get(i + 2).equals("hamilton") || arguments.get(i + 2).equals("jefferson") ||
                        arguments.get(i + 2).equals("huntington"))) {
                    this.algorithm = arguments.get(i + 2);
                    this.reps = (Integer.parseInt(arguments.get(i + 1)));
                } else if ((arguments.get(i).equals("-fr")) && arguments.get(i + 2).matches("[0-9]+")
                        && (arguments.get(i + 1).equals("benefit") || arguments.get(i + 1).equals("alpha"))) {
                    this.format = arguments.get(i + 1);
                    this.reps = (Integer.parseInt(arguments.get(i + 2)));
                } else if (arguments.get(i).equals("-fa") && (arguments.get(i + 1).equals("benefit") || arguments.get(i + 1).equals("alpha"))
                        && (arguments.get(i + 2).equals("hamilton") || arguments.get(i + 2).equals("jefferson") ||
                        arguments.get(i + 2).equals("huntington"))
                ) {
                    this.format = arguments.get(i + 1);
                    this.algorithm = arguments.get(i + 2);
                } else if (arguments.get(i).equals("-af") && (arguments.get(i + 2).equals("benefit") || arguments.get(i + 2).equals("alpha"))
                        && (arguments.get(i + 1).equals("hamilton") || arguments.get(i + 1).equals("jefferson") ||
                        arguments.get(i + 1).equals("huntington"))
                ) {
                    this.format = arguments.get(i + 2);
                    this.algorithm = arguments.get(i + 1);
                } else if ((arguments.get(i).equals("-ar"))
                        && arguments.get(i + 2).matches("[0-9]+") && (arguments.get(i + 1).equals("hamilton") || arguments.get(i + 1).equals("jefferson") ||
                        arguments.get(i + 1).equals("huntington"))) {
                    this.algorithm = arguments.get(i + 1);
                    this.reps = (Integer.parseInt(arguments.get(i + 2)));
                } else if (arguments.get(i).equals("-rfa") && arguments.get(i + 1).matches("[0-9]+") && (arguments.get(i + 2).equals("benefit") || arguments.get(i + 2).equals("alpha"))
                        && (arguments.get(i + 3).equals("hamilton") || arguments.get(i + 3).equals("jefferson") ||
                        arguments.get(i + 3).equals("huntington"))
                ) {

                    this.reps = (Integer.parseInt(arguments.get(i + 1)));
                    this.format = arguments.get(i + 2);
                    this.algorithm = arguments.get(i + 3);
                } else if (arguments.get(i).equals("-raf") && arguments.get(i + 1).matches("[0-9]+") && (arguments.get(i + 3).equals("benefit") || arguments.get(i + 3).equals("alpha"))
                        && (arguments.get(i + 2).equals("hamilton") || arguments.get(i + 2).equals("jefferson") ||
                        arguments.get(i + 2).equals("huntington"))
                ) {
                    this.reps = (Integer.parseInt(arguments.get(i + 1)));
                    this.format = arguments.get(i + 3);
                    this.algorithm = arguments.get(i + 2);
                } else if (arguments.get(i).equals("-fra") && arguments.get(i + 2).matches("[0-9]+") && (arguments.get(i + 1).equals("benefit") || arguments.get(i + 1).equals("alpha"))
                        && (arguments.get(i + 3).equals("hamilton") || arguments.get(i + 3).equals("jefferson") ||
                        arguments.get(i + 3).equals("huntington"))
                ) {
                    this.reps = (Integer.parseInt(arguments.get(i + 2)));
                    this.format = arguments.get(i + 1);
                    this.algorithm = arguments.get(i + 3);
                } else if (arguments.get(i).equals("-far") && arguments.get(i + 3).matches("[0-9]+") && (arguments.get(i + 1).equals("benefit") || arguments.get(i + 1).equals("alpha"))
                        && (arguments.get(i + 2).equals("hamilton") || arguments.get(i + 2).equals("jefferson") ||
                        arguments.get(i + 2).equals("huntington"))
                ) {
                    this.reps = (Integer.parseInt(arguments.get(i + 3)));
                    this.format = arguments.get(i + 1);
                    this.algorithm = arguments.get(i + 2);
                } else if (arguments.get(i).equals("-afr") && arguments.get(i + 3).matches("[0-9]+") && (arguments.get(i + 2).equals("benefit") || arguments.get(i + 2).equals("alpha"))
                        && (arguments.get(i + 1).equals("hamilton") || arguments.get(i + 1).equals("jefferson") ||
                        arguments.get(i + 1).equals("huntington"))
                ) {
                    this.reps = (Integer.parseInt(arguments.get(i + 3)));
                    this.format = arguments.get(i + 2);
                    this.algorithm = arguments.get(i + 1);
                    this.algorithm = arguments.get(i + 1);
                } else if (arguments.get(i).equals("-arf") && arguments.get(i + 2).matches("[0-9]+") && (arguments.get(i + 3).equals("benefit") || arguments.get(i + 3).equals("alpha"))
                        && (arguments.get(i + 1).equals("hamilton") || arguments.get(i + 1).equals("jefferson") ||
                        arguments.get(i + 1).equals("huntington"))
                ) {
                    this.reps = (Integer.parseInt(arguments.get(i + 2)));
                    this.format = arguments.get(i + 3);
                    this.algorithm = arguments.get(i + 1);
                }
//            }else if (arguments.get(i).substring(0,1).equals("-")
//                && arguments.get(i).substring(1).matches("(a)?(f)?(r)?"))
            }
        }catch(Exception e) {

            throw new IndexOutOfBoundsException("Invalid flag value pairs." +
                    " Please input the correct amount of arguments.");
        }
//        System.out.println("REPS" + this.reps);
//        System.out.println("ALGO" + this.algorithm);
//        System.out.println("format" + this.format);

        if(reps == -1 && ( (arguments.get(1).contains("r") && !arguments.get(1).contains("--"))  || arguments.contains("--reps"))  ){
                //System.out.println("reps issue1 ");
            if(counter ==0){

                System.out.println("Invalid inputs. Please input the correct flag value pairs." +
                        " Will display default Representatives, Format,  Hamilton ");
                counter++;
            }
                config.setRepresentatives(435);
        }else if( reps ==-1 && !((arguments.get(1).contains("r") && !arguments.get(1).contains("--")) || arguments.contains("--reps"))  ){
            config.setRepresentatives(435);
        }
        if(this.reps > 0 ){
                config.setRepresentatives(this.reps);

        }
        if(this.reps < -1 ){
            if(counter == 0){
            System.out.println("You can't put a negative number. Please input the correct flag value pairs." +
                    "Will display default Representatives, Format,  Hamilton ");
            counter++;
            }
            config.setRepresentatives(this.reps);
        }
        if((this.reps <=0) && (this.reps != -1)){
            throw new IllegalArgumentException("Invalid number of representatives and we'll proceed with default");
        }

        if(this.algorithm.equals("hamilton")){
            config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
        }
        if(this.algorithm.equals("huntington")){
            config.setApportionmentStrategy(new HuntingtonHillApportionmentStrategy());
        }
        if(this.algorithm.equals("jefferson")){
            config.setApportionmentStrategy(new JeffersonApportionmentStrategy());
        }

//        if((!this.algorithm.matches("jefferson") && !this.algorithm.matches("huntington") && !this.algorithm.matches("hamilton"))
//                && this.arguments.get(1).contains("a")) {
//            System.out.println("algorithm issue1");
//            if(counter == 0){
//                System.out.println("Invalid inputs. Please input the correct flag value pairs." +
//                        " Will display default Representatives, Format,  Hamilton ");
//                counter++;
//            }
//            config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
//
//        }
         if(this.algorithm.equals("") && !( (arguments.get(1).contains("a") && !arguments.get(1).contains("--")) || arguments.contains("--algorithm")) ){
            config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
        }
        else if(this.algorithm.equals("") && ( (arguments.get(1).contains("a") || !arguments.get(1).contains("--")) || arguments.contains("--algorithm")) ){
            //System.out.println("hello");
            if(counter == 0){
                System.out.println("Invalid inputs. Please input the correct flag value pairs." +
                        " Will display default Representatives, Format,  Hamilton ");
                counter++;
            }
            config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
        }

        if(this.format.equals("benefit")){
            config.setApportionmentFormat(new RelativeBenefitFormat());
        }
        else if(this.format.equals("alpha")){
            config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
        }

//        else if((!this.format.matches("benefit") && !this.format.matches("alpha")) && !this.format.matches("")) {
//            System.out.println("format issue1");
//            if(counter == 0){
//                System.out.println("Invalid inputs. Please input the correct flag value pairs." +
//                        " Will display default Representatives, Format,  Hamilton ");
//                counter++;
//            }
//            config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
//
//        }
        else if (this.format.equals("") && !( (arguments.get(1).contains("f") && !arguments.get(1).contains("--") )|| arguments.contains("--format")) ){
            config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
        }

        else if(this.format.equals("") && ( (arguments.get(1).contains("f") && !arguments.get(1).contains("--") )|| arguments.contains("--format")) ){
            //System.out.println("format issue 2");
            if(counter == 0){
                System.out.println("Invalid inputs. Please input the correct flag value pairs." +
                        " Will display default Representatives, Format,  Hamilton ");
                counter++;
            }
            config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
        }
        return config;
    }

    private void setDefaultConfiguration() {
        System.out.println("Displaying Default: Strategy: Hamilton, Representatives: 435, Format: Alphabetical");
        config = new Configuration();
        config.setApportionmentStrategy(new HamiltonApportionmentStrategy());
        config.setRepresentatives(435);
        config.setApportionmentFormat(new AlphabeticalApportionmentFormat());
    }

    private void configureStateReader() {
        String filename = arguments.get(FILENAME_INDEX);
        setStateReaderFromFilename(filename);
    }


    private void checkForRepresentativeCount() {
        if (arguments.size() < 2) {
            return;
        }
        try {
            int representativeCount = Integer.parseInt(arguments.get(REPRESENTATIVES_INDEX));
            if (representativeCount <= 0) {
                throw new IllegalArgumentException("Error: Invalid representative count : " + representativeCount + " - number must be positive");
            }
            config.setRepresentatives(representativeCount);
        } catch (NumberFormatException ignored) {
        }
    }

    private void setStateReaderFromFilename(String filename) {
        if (filename.toLowerCase().endsWith(".csv")) {
            setConfigurationToCSVReader(filename);
        } else if (filename.toLowerCase().endsWith(".xlsx")) {
            setConfigurationToXLSXReader(filename);
        } else {
            throw new IllegalArgumentException("Error: invalid file type. The system currently supports:\n" +
                    "\t.csv, .xlsx");
        }
    }

    private boolean filenameExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    private void setConfigurationToCSVReader(String filename) {

        config.setStateReader(new CSVStateReader(filename));
    }

    private void setConfigurationToXLSXReader(String filename) {
        config.setStateReader(new ExcelStateReader(filename));
    }
}
