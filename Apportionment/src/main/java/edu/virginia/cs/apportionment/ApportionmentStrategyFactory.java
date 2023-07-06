package edu.virginia.cs.hw3;

public class ApportionmentStrategyFactory {
    public ApportionmentStrategy getStrategy(String algorithmName){
        if(algorithmName.contains("Hamilton")){
            return new HamiltonApportionmentStrategy();
        }
        else if(algorithmName.contains("HuntingtonHill")){
            return new HuntingtonHillApportionmentStrategy();
        }
        else if(algorithmName.contains("Jefferson")){
            return new JeffersonApportionmentStrategy();
        }
        else{
            throw new IllegalArgumentException("Invalid Strategy Type");
        }
    }
}
