package com.automation.mobile.swipe.step;

import com.automation.mobile.swipe.logic.SwipeLogic;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;

public class SwipeStep {

    private SwipeLogic swipeLogic;

    private String scenario;

    public SwipeStep() {
        this.swipeLogic = new SwipeLogic();
    }

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario.getName();
    }

    @Then("o swipe deve ser realizado com sucesso")
    public void swipe_deve_ser_realizado_com_sucesso() {
        this.swipeLogic.realizarScroll();
        this.swipeLogic.assertSwipeRealizado(scenario);
    }

}
