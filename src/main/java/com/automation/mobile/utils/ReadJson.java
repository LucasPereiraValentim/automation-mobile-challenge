package com.automation.mobile.utils;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

@Slf4j
public class ReadJson {

    private JSONParser jsonParser;

    public ReadJson() {
        this.jsonParser = new JSONParser();
    }

    public JSONObject getCapabilities() {
        try {
            if (isDeviceAndroid()) {
                return (JSONObject) jsonParser.parse(new FileReader(
                        System.getProperty("user.dir") +
                                "/src/test/resources/device-config/android-device-config.json"));

            } else if (isDeviceIOS()) {
                log.info("IOS");
                return null;
            } else {
                throw new AutomationException("Plataforma invalida!!");
            }
        } catch (Exception e) {
            throw new AutomationException("Erro ao obter plataforma de execução.");
        }
    }


    private boolean isDeviceAndroid() {
        return DriverFactory.getPlatformName().equalsIgnoreCase("android");
    }

    private boolean isDeviceIOS() {
        return DriverFactory.getPlatformName().equalsIgnoreCase("ios");
    }


    public JSONObject readToJsonFixtures(String nameFile) {
        String pathFile = "/src/test/resources/fixtures/" + nameFile;
        try {
            return (JSONObject) jsonParser.parse(new FileReader(
                    System.getProperty("user.dir") + pathFile));
        } catch (Exception e) {
            throw new AutomationException("Erro ao realizar leitura de json. Path file: " + pathFile);
        }
    }
}
