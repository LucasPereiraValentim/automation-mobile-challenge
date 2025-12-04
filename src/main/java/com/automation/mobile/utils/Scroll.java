package com.automation.mobile.utils;

import com.automation.mobile.enums.ScrollDirection;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

import static com.automation.mobile.utils.UtilsMobile.getDriver;
import static com.automation.mobile.utils.UtilsMobile.isVisible;

@Slf4j
public class Scroll {

    private static final String DESCRICAO_ESPERA_BOTAO = "Aguardando elemento ficar disponível para realizar scroll...";

    /**
     * Método público principal para iniciar o scroll em uma direção específica.
     *
     * @param scrollDirection Direção do scroll (TOP, DOWN, LEFT, RIGHT).
     * @param elementInit     Elemento inicial de referência para o cálculo do scroll.
     * @param elementMoveTo   Elemento alvo que esperamos encontrar após o scroll.
     */
    public static void scroll(ScrollDirection scrollDirection, WebElement elementInit, WebElement elementMoveTo) {
        switch (scrollDirection) {
            case TOP:
                scrollTop(elementInit, elementMoveTo);
                break;
            case DOWN:
                scrollDown(elementInit, elementMoveTo);
                break;
            case LEFT:
                scrollLeft(elementInit, elementMoveTo);
                break;
            case RIGHT:
                scrollRight(elementInit, elementMoveTo);
                break;
            default:
                throw new AutomationException("Direção de scroll incorreta ou não suportada: " + scrollDirection);
        }
    }

    private static void scrollLeft(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit, DESCRICAO_ESPERA_BOTAO);
        int centerY = elementInit.getRect().y + (elementInit.getSize().height / 2);
        int startX = (int) (elementInit.getRect().x + (elementInit.getSize().width * 0.9));
        int endX = (int) (elementInit.getRect().x + (elementInit.getSize().width * 0.1));

        scroll(startX, centerY, endX, centerY, elementMoveTo);
    }

    private static void scrollRight(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit, DESCRICAO_ESPERA_BOTAO);
        int centerY = elementInit.getRect().y + (elementInit.getSize().height / 2);
        int startX = (int) (elementInit.getRect().x + (elementInit.getSize().width * 0.1));
        int endX = (int) (elementInit.getRect().x + (elementInit.getSize().width * 0.9));
        scroll(startX, centerY, endX, centerY, elementMoveTo);
    }

    private static void scrollDown(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit, DESCRICAO_ESPERA_BOTAO);
        int centerX = elementInit.getRect().x + (elementInit.getSize().width / 2);
        int startY = (int) (elementInit.getRect().y + (elementInit.getSize().height * 0.8));
        int endY = (int) (elementInit.getRect().y + (elementInit.getSize().height * 0.2));

        scroll(centerX, startY, centerX, endY, elementMoveTo);
    }

    private static void scrollTop(WebElement elementInit, WebElement elementMoveTo) {
        UtilsMobile.waitElementToBeClickable(elementInit, DESCRICAO_ESPERA_BOTAO);
        int centerX = elementInit.getRect().x + (elementInit.getSize().width / 2);
        int startY = (int) (elementInit.getRect().y + (elementInit.getSize().height * 0.2)); // 20% (cima) para iniciar
        int endY = (int) (elementInit.getRect().y + (elementInit.getSize().height * 0.8));   // 80% (baixo) para terminar

        scroll(centerX, startY, centerX, endY, elementMoveTo);
    }

    /**
     * Executa o gesto de swipe usando W3C Pointer Actions.
     *
     * @param startX        Coordenada X inicial.
     * @param startY        Coordenada Y inicial.
     * @param endX          Coordenada X final.
     * @param endY          Coordenada Y final.
     * @param elementMoveTo Elemento alvo a ser verificado após o swipe.
     */
    private static void scroll(int startX, int startY, int endX, int endY, WebElement elementMoveTo) {

        AppiumDriver driver = getDriver();

        for (int i = 1; i < 20; i++) {
            String msg = "Realizando Scroll...";
            log.info("Procurando elemento. Tentativa N°" + i);
            log.info(msg);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 0); // Offset de tempo inicial 0

            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));

            swipe.addAction(finger.createPointerDown(0));

            swipe.addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY));

            swipe.addAction(finger.createPointerUp(0));

            driver.perform(Collections.singletonList(swipe));

            if (isVisible(elementMoveTo)) {
                msg = "Elemento encontrado";
                log.info(msg);
                break;
            }
        }
    }
}
