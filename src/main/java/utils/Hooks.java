package utils;

import cucumber.api.java.After;
import pageRepository.journeyPage;

public class Hooks {
journeyPage1 jp=new journeyPage();
	
	@After
    public void afterScenario(){
        System.out.println("This will run after the Scenario");
        jp.closeApplication();
    }
}
