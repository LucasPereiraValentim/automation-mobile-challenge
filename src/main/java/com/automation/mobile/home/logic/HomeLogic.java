package com.automation.mobile.home.logic;


import com.automation.mobile.home.page.HomePage;
import com.automation.mobile.utils.GeneratorEvidence;
import com.automation.mobile.utils.UtilsMobile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeLogic {

    private HomePage homePage;

    public HomeLogic() {
        this.homePage = new HomePage();
    }

    public void acessarAbaLogin() {
        String desc = "Clicando na aba do menu";
        log.info(desc);
        GeneratorEvidence.logStep(desc);
        UtilsMobile.click(homePage.getABA_LOGIN(), desc);
    }

    public void acessarAbaSwipe() {
        String desc = "Clicando na aba do swipe";
        log.info(desc);
        GeneratorEvidence.logStep(desc);
        UtilsMobile.click(homePage.getABA_SWIPE(), desc);
    }


}
