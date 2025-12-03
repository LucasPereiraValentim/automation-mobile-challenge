package com.automation.mobile.home.page;


import com.automation.mobile.utils.UtilsMobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HomePage {
	
	@AndroidFindBy(id = "com.Advantage.aShopping:id/imageViewMenu")
	private WebElement MENU_PRINCIPAL;
	
	@AndroidFindBy(id = "com.Advantage.aShopping:id/editTextSearch")
	private WebElement CAMPO_SEARCH;

	@AndroidFindBy(xpath = "//*[@resource-id='com.Advantage.aShopping:id/textViewMenuUser']")
	private WebElement BOTAO_LOGIN;

    @AndroidFindBy(accessibility = "Login")
    @iOSXCUITFindBy(accessibility = "")
    private WebElement ABA_LOGIN;
	
	public HomePage() {
		PageFactory.initElements(new AppiumFieldDecorator(UtilsMobile.getDriver()), this);
	}
}
