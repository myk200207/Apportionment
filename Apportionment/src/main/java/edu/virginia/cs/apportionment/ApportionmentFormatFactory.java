package edu.virginia.cs.hw3;

public class ApportionmentFormatFactory {
    public ApportionmentFormat getFormat(String formatName){
        if(formatName.contains("alpha")){
            return new AlphabeticalApportionmentFormat();
        }
        else if(formatName.contains("benefit")){
            return new RelativeBenefitFormat();
        }
        else{
            throw new IllegalArgumentException("Invalid Apportionment Format Type");
        }
    }
}
