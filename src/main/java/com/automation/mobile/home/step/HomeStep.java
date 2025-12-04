package com.automation.mobile.home.step;


import com.automation.mobile.home.logic.HomeLogic;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class HomeStep {

    private HomeLogic homeLogic;

    public HomeStep() {
        this.homeLogic = new HomeLogic();
    }

    @Given("que acesso a home do app")
    public void clico_na_aba_login() {
        this.homeLogic.acessarAbaLogin();
    }

    @And("clico na aba swipe")
    public void clico_na_aba_swipe() {
        this.homeLogic.acessarAbaSwipe();
    }

}
