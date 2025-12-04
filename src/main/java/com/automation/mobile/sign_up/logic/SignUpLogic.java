package com.automation.mobile.sign_up.logic;

import com.automation.mobile.sign_up.page.SignUpPage;
import com.automation.mobile.utils.Assertion;
import com.automation.mobile.utils.ReadJson;
import com.automation.mobile.utils.UtilsMobile;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SignUpLogic {

    private SignUpPage signUpPage;

    private String senha;

    private ReadJson readJson;

    public SignUpLogic() {
        this.signUpPage = new SignUpPage();
        this.readJson = new ReadJson();
    }

    public void clicarAbaBotaoSignUp() {
        String desc = "Clicando na aba \"Sign Up\"";
        log.info(desc);
        UtilsMobile.click(this.signUpPage.getBTN_SIGN_UP(), desc);
    }

    public void preencherCampoEmailCadastro() {
        String email = new Faker().internet().emailAddress();
        String desc = "Preenchendo o campo email com valor: " + email;
        log.info(desc);
        UtilsMobile.sendKeys(this.signUpPage.getCAMPO_EMAIL_CADASTRO(), email, desc);
    }

    public void preencherCampoSenhaCadastro() {
        senha = new Faker().internet().password(8, 9);
        String desc = "Preenchendo o campo senha com valor: " + senha;
        log.info(desc);
        UtilsMobile.sendKeys(this.signUpPage.getCAMPO_SENHA_CADASTRO(), senha, desc);
    }

    public void preencherCampoConfirmacaoSenhaCadastro() {
        String desc = "Preenchendo o campo confirmação ded senha com valor: " + senha;
        log.info(desc);
        UtilsMobile.sendKeys(this.signUpPage.getCAMPO_CONFIRMACAO_SENHA_CADASTRO(), senha, desc);
    }

    public void clicarBotaoSignUp() {
        String desc = "Clicando no botão \"SIGN UP\"";
        log.info(desc);
        UtilsMobile.click(this.signUpPage.getBOTAO_REALIZAR_CADASTRO(), desc);
    }

    public void assertCadastroSucesso(String testName) {
        String messagem = readJson.readToJsonFixtures("message_success_create_user.json").get("message").toString();
        String description = "Validação Cadastro de Usuário!";
        Assertion.validationTestByText(this.signUpPage.getTEXTO_MENSAGEM_SUCESSO_CADASTRO(), testName, description, messagem);
    }

}
