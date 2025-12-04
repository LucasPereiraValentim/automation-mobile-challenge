package com.automation.mobile.hooks;

import com.automation.mobile.utils.GeneratorEvidence;
import com.automation.mobile.utils.UtilsMobile;
import io.cucumber.java.*;

public class Hooks {

    @Before
    public void init() {
        UtilsMobile.getDriver();
    }

    @After
    public void after() {
        UtilsMobile.quitDriver();
    }
}
