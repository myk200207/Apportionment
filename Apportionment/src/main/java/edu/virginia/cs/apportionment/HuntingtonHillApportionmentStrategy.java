package edu.virginia.cs.hw3;

import java.util.*;

public class HuntingtonHillApportionmentStrategy extends ApportionmentStrategy {
    private List<State> stateList;
    private int representatives;
    private double priorityScore;
    public TreeMap<String,Integer> stateNameNRep = new TreeMap<>();
    public TreeMap<Double,State> priNState = new TreeMap<>(Comparator.reverseOrder()); // research comparator
    private Apportionment apportionment;
    public HashMap<State,Integer> apportioned = new HashMap<>();


    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        initializeFields(stateList, representatives);
        distributeReps();
        pairStatesWithReps(stateList);
        this.apportionment = new Apportionment(apportioned);
        return apportionment;
    }

    private void pairStatesWithReps(List<State> stateList) {
        for(String j: stateNameNRep.keySet()){
            for(int i = 0; i< stateList.size(); i++){
                if(stateList.get(i).getName().equals(j)){
                    apportioned.put(stateList.get(i), stateNameNRep.get(j));
                }
            }
        }
    }

    private void distributeReps() {
        while(this.representatives>0){
            priNState();
            String topStateName = priNState.get(priNState.firstKey()).getName();
            int updateRep = stateNameNRep.get(topStateName)+1;
            stateNameNRep.put(topStateName, updateRep);
            priNState.remove(priNState.firstKey());
            this.representatives--;
          }
    }

    private void initializeFields(List<State> stateList, int representatives) {
        if(stateList.size() > representatives){
            throw new IllegalArgumentException("Insufficient representatives for each state to have one.");
        }
        this.stateList = stateList;
        this.representatives = representatives;
        initialRep();
    }
    public void initialRep() {
        for (State state : stateList) {
            stateNameNRep.put(state.getName(),1);
            this.representatives--;
        }
    }
    public void priNState(){
        for (State state : stateList){
            priorityScore = state.getPopulation()/Math.sqrt(stateNameNRep.get(state.getName())*(stateNameNRep.get(state.getName())+1));
            priNState.put(priorityScore, state);
        }
    }
}