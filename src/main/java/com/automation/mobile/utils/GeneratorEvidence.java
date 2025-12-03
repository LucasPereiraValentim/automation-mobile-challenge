package com.automation.mobile.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class GeneratorEvidence {

    @Step("{descricao}")
    public static void logStep(String descricao) {
        takeScreenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] takeScreenshot() {
        return UtilsMobile.getDriver().getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
    }
}
