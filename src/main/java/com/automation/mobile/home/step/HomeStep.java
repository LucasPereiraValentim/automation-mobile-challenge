package com.automation.mobile.home.step;


import com.automation.mobile.home.logic.HomeLogic;
import io.cucumber.java.en.Given;

public class HomeStep {

    private HomeLogic homeLogic;

    public HomeStep() {
        this.homeLogic = new HomeLogic();
    }

    @Given("que acesso a home do app")
    public void clico_no_menu_lateral() {
        this.homeLogic.acessarAbaLogin();
    }

}
