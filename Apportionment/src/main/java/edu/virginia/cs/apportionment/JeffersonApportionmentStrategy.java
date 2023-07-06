package edu.virginia.cs.hw3;
import java.util.*;

public class JeffersonApportionmentStrategy extends ApportionmentStrategy{
    private List<State> stateList;
    public static HashMap<State,Integer> StatesNRep = new HashMap<>();
    public static HashMap<State,Double> StatesNRepRaw = new HashMap<>();
    private int NumberOfRepToAllocate;
    public int NumberOfRepresentatives;
    private double divisor;
    public double OriginalDivisor;
    public Apportionment apportionment;
    public Apportionment apportionmentraw;
    public DecimalApportionment decimalApportionment;
    public int calCRep;
    public double calcRaw;
    @Override
    public Apportionment getApportionment(List<State> stateList, int representatives) {
        initializeFields(stateList, representatives);
        divisor = getDivisor();
        OriginalDivisor = divisor;
        //calculate how many reps to distribute
//        System.out.println( "DIVISOR " + divisor);
//        System.out.println("STATELIST " + stateList);
//        System.out.println("NUMBEROFREP " + NumberOfRepresentatives);
        AllocateRepresentatives(stateList, representatives);
        RawRepresentatives(stateList,representatives);

        decimalApportionment = new DecimalApportionment(RawRepresentatives(stateList,representatives));
        //RelativeBenefitFormat.RelativeBenefitFormat(apportionment,decimalApportionment); // MAY NEED TO UNCOMMENT
        this.apportionment = new Apportionment(StatesNRep);

        //System.out.println("NUMOFREPS REMAINING " + NumberOfRepresentatives);
//        System.out.println("STATENREPS " + StatesNRep);
        return apportionment;

    }

    public HashMap<State,Double> RawRepresentatives(List<State> stateList, int representatives){
        NumberOfRepresentatives = representatives;

        for (State state : stateList) {
            calcRaw = state.getPopulation() / OriginalDivisor;
            //System.out.println(calcRaw);
            StatesNRepRaw.put(state, calcRaw);
            NumberOfRepresentatives -= (int)calcRaw;
        }
        //System.out.println(StatesNRepRaw);
        return StatesNRepRaw;
    }

    private void AllocateRepresentatives(List<State> stateList, int representatives) {
        while(NumberOfRepresentatives>0){
            //System.out.println("In the while loop");
            for (State state : stateList) {
//                System.out.println(state.getName() + " - " + state.getPopulation());
//                System.out.println(divisor);
                calCRep = (int)getCalCRep(state);
                StatesNRep.put(state, calCRep);
                NumberOfRepresentatives -= calCRep;

            }
            if(NumberOfRepresentatives > 0 ){
                this.NumberOfRepresentatives = representatives;
                divisor --;

            }

        }

    }

    private double getCalCRep(State state) {
        return (state.getPopulation() / divisor);
    }

    private void initializeFields(List<State> stateList, int representatives) {
        this.stateList = stateList;
        this.NumberOfRepresentatives = representatives;
        //NumberOfRepToAllocate -= representatives;
    }

    private double getDivisor() {
        int totalPopulation = getTotalPopulation();
        return (double) totalPopulation / NumberOfRepresentatives;
    }
    private int getTotalPopulation() {
        int totalPopulation = 0;
        for (State state : stateList) {
            totalPopulation += state.getPopulation();
        }
        return totalPopulation;
    }
}
