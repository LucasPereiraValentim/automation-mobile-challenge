package com.automation.mobile.sign_up.step;

import com.automation.mobile.sign_up.logic.SignUpLogic;
import io.cucumber.java.en.When;

public class SignUpStep {

    private SignUpLogic signUpLogic;

    public SignUpStep() {
        this.signUpLogic = new SignUpLogic();
    }

    @When("preencho campo e-mail")
    public void preencher_campo_email() {
        this.signUpLogic.preencherCampoEmailCadastro();
    }
}
