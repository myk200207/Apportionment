package edu.virginia.cs.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class HuntingtonApportionmentStrategyTester extends HuntingtonHillApportionmentStrategy{
    ArrayList<State> stateTest;
    @BeforeEach
    public void mockStateList(){
        stateTest = new ArrayList();
        State Delaware = new State("Delaware", 989948);
        State Maryland = new State("Maryland", 6177224);
        State Pennsylvania = new State("Pennsylvania", 13002700);
        State Virginia = new State("Virginia", 8631393);
        State WestVirginia = new State("West Virginia", 1793716);
        stateTest.add(Delaware);
        stateTest.add(Maryland);
        stateTest.add(Pennsylvania);
        stateTest.add(Virginia);
        stateTest.add(WestVirginia);
    }
    @Test
    @DisplayName("5 State Equivalence Test")
    public void HuntingtonHillApportionmentStrategy5StateTest(){
        getApportionment(stateTest, 25);
        //THIS STREAM NEEDS TO BE PUT IN OUR REAL CODE SOMEWHERE. IS THE ONLY CURRENT WAY TO SORT IT ALPHABETICALLY
        apportioned.entrySet().stream()
                .sorted((s1,s2)-> String.CASE_INSENSITIVE_ORDER.compare(s1.getKey().getName(),s2.getKey().getName()))
                .map(s1 -> s1.getKey().getName()+" - "+s1.getValue())
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Insufficient Representatives Exception Test")
    public void HuntingtonHillApportionmentStrategyInsufficientRepresentatives(){
        assertThrows(IllegalArgumentException.class, () -> getApportionment(stateTest, 4));
    }
}
