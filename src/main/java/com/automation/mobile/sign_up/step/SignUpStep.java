package com.automation.mobile.sign_up.step;

import com.automation.mobile.sign_up.logic.SignUpLogic;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpStep {

    private SignUpLogic signUpLogic;

    private String scenario;

    public SignUpStep() {
        this.signUpLogic = new SignUpLogic();
    }

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario.getName();
    }

    @When("preencho campo e-mail")
    public void preencher_campo_email() {
        this.signUpLogic.clicarAbaBotaoSignUp();
        this.signUpLogic.preencherCampoEmailCadastro();
    }


    @And("preencho o campo senha")
    public void preencher_campo_senha() {
        this.signUpLogic.preencherCampoSenhaCadastro();
    }

    @And("preencho o campo de confirmacao de senha")
    public void preencher_campo_confirmacao_senha() {
        this.signUpLogic.preencherCampoConfirmacaoSenhaCadastro();
        this.signUpLogic.clicarBotaoSignUp();
    }

    @Then("deve exibir mensagem de sucesso de cadastro")
    public void deve_exibir_mensagem_de_sucesso_cadastro() {
        this.signUpLogic.assertCadastroSucesso(scenario);
    }

}
