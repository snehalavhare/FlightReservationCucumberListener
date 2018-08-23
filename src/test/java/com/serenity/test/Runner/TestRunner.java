package com.serenity.test.Runner;

import java.io.File;

import org.testng.annotations.AfterClass;
/*
import org.junit.AfterClass;
import org.junit.runner.RunWith;*/
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/flight_featuredetails",
		glue = {"com.serenity.test.stepDefinition"},
		monochrome = true,
		format = {"pretty", "html:target/cucumber-reports/htmlreport.html"},
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/Extent.html"},
		tags = {"@First, @Second"}
	)

public class TestRunner extends AbstractTestNGCucumberTests
{
	
	@AfterClass
	public static void wrieExtentReport()
	{
		Reporter.loadXMLConfig(new File("C:\\EclipseWorkspace\\FlightReservationCucumberListener\\ExtentConfig.xml"));
		Reporter.setSystemInfo("User", System.getProperty("user.name"));
        Reporter.setSystemInfo("OS", "Windows");
        Reporter.setTestRunnerOutput("Sample test runner output message");
	}
		
}

