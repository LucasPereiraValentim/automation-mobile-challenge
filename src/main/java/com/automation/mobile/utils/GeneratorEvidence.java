package com.automation.mobile.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

@Slf4j
public class GeneratorEvidence {

    @Step("{descricao}")
    public static void logStep(String descricao) {
        Allure.step(descricao);
        Allure.addAttachment(descricao,
                "image/png",
                new ByteArrayInputStream(takeScreenshot()),
                ".png");
    }

    private static byte[] takeScreenshot() {

            WebDriver driver = UtilsMobile.getDriver();
            if (driver instanceof TakesScreenshot) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                log.error("O driver atual não suporta screenshots.");
                return new byte[0];
            }
        }

    }
