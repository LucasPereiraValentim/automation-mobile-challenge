package com.automation.mobile.swipe.page;

import com.automation.mobile.utils.UtilsMobile;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class SwipePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"FULLY OPEN SOURCE\")")
    @iOSXCUITFindBy(xpath = "")
    private WebElement ELEMENTO_SCROLL;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"GREAT COMMUNITY\"]")
    private WebElement TEXT_GREAT_COMMUNITY;

    public SwipePage() {
        PageFactory.initElements(new AppiumFieldDecorator(UtilsMobile.getDriver()), this);
    }
}
