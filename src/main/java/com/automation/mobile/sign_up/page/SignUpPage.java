package com.automation.mobile.sign_up.page;

import com.automation.mobile.utils.UtilsMobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class SignUpPage {

    @AndroidFindBy(accessibility = "button-sign-up-container")
    @iOSXCUITFindBy(accessibility = "")
    private WebElement BTN_SIGN_UP;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Email\")")
    @iOSXCUITFindBy(accessibility = "")
    private WebElement CAMPO_EMAIL_CADASTRO;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Password\")")
    @iOSXCUITFindBy(xpath = "")
    private WebElement CAMPO_SENHA_CADASTRO;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Confirm password\")")
    @iOSXCUITFindBy(xpath = "")
    private WebElement CAMPO_CONFIRMACAO_SENHA_CADASTRO;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"button-SIGN UP\"]")
    @iOSXCUITFindBy(xpath = "")
    private WebElement BOTAO_REALIZAR_CADASTRO;

    @AndroidFindBy(id = "android:id/message")
    private WebElement TEXTO_MENSAGEM_SUCESSO_CADASTRO;

    public SignUpPage() {
        PageFactory.initElements(new AppiumFieldDecorator(UtilsMobile.getDriver()), this);
    }
}
