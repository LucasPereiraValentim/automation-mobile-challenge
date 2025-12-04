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

    @AndroidFindBy(accessibility = "Login")
    @iOSXCUITFindBy(accessibility = "")
    private WebElement ABA_LOGIN;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Swipe\")")
    @iOSXCUITFindBy(accessibility = "")
    private WebElement ABA_SWIPE;
	
	public HomePage() {
		PageFactory.initElements(new AppiumFieldDecorator(UtilsMobile.getDriver()), this);
	}

}
