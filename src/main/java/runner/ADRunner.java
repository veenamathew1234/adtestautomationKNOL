package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= "classpath:features"
		,glue= {"stepDefinitions"},
		format= {"pretty","html:test-outout"}
		)
public class ADRunner {

}
