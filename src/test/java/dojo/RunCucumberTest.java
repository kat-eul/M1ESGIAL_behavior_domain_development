package dojo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", monochrome = true, plugin = {"pretty", "html:target/site/cucumber.html"})
public class RunCucumberTest{

}