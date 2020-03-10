package utils;

import cucumber.api.java.After;
import pageRepository.journeyPage1;

public class Hooks {
journeyPage1 jp=new journeyPage1();
	
	@After
    public void afterScenario(){
        System.out.println("This will run after the Scenario");
        jp.closeApplication();
    }
}
