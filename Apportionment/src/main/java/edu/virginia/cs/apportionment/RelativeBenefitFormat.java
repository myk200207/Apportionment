package edu.virginia.cs.hw3;

import java.util.*;
import java.util.stream.*;
import java.util.HashMap;
import java.util.Map;

public class RelativeBenefitFormat extends ApportionmentFormat {
    private Apportionment apportionment;
    public DecimalApportionment decimalApportionment;
    public String output = "";
    public int totalPop = 0;
    public int totalRep = 0;
    public HashMap<State, Double> statesNRepRaw = new HashMap<>();
    public double divisor = 0;




    //    public String RelativeBenefitFormat(HashMap<State, Integer> statesNRep, HashMap<State, Double> statesNRepRaw) {
//        TreeMap<Double,rawState> stateAndRep = new TreeMap<>(Comparator.reverseOrder());
//        String differenceSign = "";
//        for (State state : statesNRepRaw.keySet()) {
//            rawState rawState = new rawState(state.getName(),statesNRep.get(state));
//           // System.out.println(rawState.getName()+ rawState.getRep());
//            double difference = statesNRep.get(state) - statesNRepRaw.get(state);
//            stateAndRep.put(difference, rawState);
//
//        }
//        //System.out.println(stateAndRep.get(-0.8089137234633353).getName());
//        for(Double difference : stateAndRep.keySet()){
//            if(difference >= 0){
//                differenceSign = "+";
//            }else{
//                differenceSign ="";
//            }
//            //System.out.printf(stateAndRep.get(difference).getName() + " - " + stateAndRep.get(difference).getRep() + " - " + differenceSign + "%.3f %n", difference);
//            output  += stateAndRep.get(difference).getName() + " - " + stateAndRep.get(difference).getRep() + " - " + differenceSign + String.format("%.3f %n", difference)+"\n";
//        }
//        return output;
//    }
public String RelativeBenefitFormat(Apportionment apportionment, DecimalApportionment decimalApportionment) {
    TreeMap<Double,rawState> stateAndRep = new TreeMap<>(Comparator.reverseOrder());
    //this.decimalApportionment = decimalApportionment;
    String differenceSign = "";
    for (State state : decimalApportionment.decimalApportionmentMap.keySet()) {
        rawState rawState = new rawState(state.getName(),apportionment.apportionmentMap.get(state));
        // System.out.println(rawState.getName()+ rawState.getRep());
        double difference = apportionment.apportionmentMap.get(state) - decimalApportionment.decimalApportionmentMap.get(state);
        stateAndRep.put(difference, rawState);

    }
    //System.out.println(stateAndRep.get(-0.8089137234633353).getName());
    for(Double difference : stateAndRep.keySet()){
        if(difference >= 0){
            differenceSign = "+";
        }else{
            differenceSign ="";
        }
        //System.out.printf(stateAndRep.get(difference).getName() + " - " + stateAndRep.get(difference).getRep() + " - " + differenceSign + "%.3f %n", difference);
        output  += stateAndRep.get(difference).getName() + " - " + stateAndRep.get(difference).getRep() + " - " + differenceSign + String.format("%.3f %n", difference);
    }
    return output;
}

    @Override
    public String getApportionmentString(Apportionment apportionment) {
        this.apportionment = apportionment;
        for(State state : apportionment.getStateSet()){
            totalPop += state.getPopulation();
        }
        totalRep = apportionment.getAllocatedRepresentatives();
        divisor = (double)totalPop/totalRep;
        for(State state: apportionment.getStateSet()){
            statesNRepRaw.put(state, state.getPopulation()/divisor);
        }
        this.decimalApportionment = new DecimalApportionment(statesNRepRaw);
        return RelativeBenefitFormat(apportionment, this.decimalApportionment);
    }
}