package edu.virginia.cs.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JeffersonApportionmentTest extends  JeffersonApportionmentStrategy{
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
    public void Test(){

        getApportionment(stateTest, 25);
//        RawRepresentatives(stateTest,25);
    }
}
