package com.serenity.test.Runner;

import java.io.File;
/*
import org.junit.AfterClass;
import org.junit.runner.RunWith;*/
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import TestBase.ITestExtent;
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
		
}

