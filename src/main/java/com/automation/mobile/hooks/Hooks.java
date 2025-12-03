package com.automation.mobile.hooks;


import com.automation.mobile.utils.UtilsMobile;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

public class Hooks {
	
    @Before
    public void init() {
    	UtilsMobile.getDriver();
    }

    @After
    public void after() {
    	
    }

    @BeforeStep
    public void beforeStep() {
        
    }

    @AfterStep
    public void afterStep() {
        
    }
}