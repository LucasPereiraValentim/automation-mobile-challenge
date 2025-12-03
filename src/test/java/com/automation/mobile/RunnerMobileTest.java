package com.automation.mobile;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features", //
        glue = "com.automation.mobile", //
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        } //
)
public class RunnerMobileTest extends AbstractTestNGCucumberTests {
}
