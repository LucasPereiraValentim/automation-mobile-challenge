package com.automation.mobile.swipe.logic;

import com.automation.mobile.enums.ScrollDirection;
import com.automation.mobile.swipe.page.SwipePage;
import com.automation.mobile.utils.Assertion;
import com.automation.mobile.utils.Scroll;
import com.automation.mobile.utils.UtilsMobile;
import org.openqa.selenium.By;

public class SwipeLogic {

    private SwipePage swipePage;

    public SwipeLogic() {
        this.swipePage = new SwipePage();
    }

    public void realizarScroll() {
        Scroll.scroll(ScrollDirection.LEFT, this.swipePage.getELEMENTO_SCROLL(), this.swipePage.getTEXT_GREAT_COMMUNITY());
    }

    public void assertSwipeRealizado(String scenario) {
        String description = "Validação Swipe Realizado";
        Assertion.validationTestByVisible(this.swipePage.getTEXT_GREAT_COMMUNITY(), scenario, description);
    }

}
