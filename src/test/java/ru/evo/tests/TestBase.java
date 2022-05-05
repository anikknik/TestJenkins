package ru.evo.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        CredentialsConfig configLogg = ConfigFactory.create(CredentialsConfig.class);

        String login = configLogg.login();
        String password = configLogg.password();

        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "100");
        String size = System.getProperty("size", "1920x1080");
        String baseUrl = System.getProperty("base_url", "https://demoqa.com");
        String remoteUrl = System.getProperty("remote_url", "selenoid.autotests.cloud/wd/hub");

        Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.browserSize = size;
        Configuration.baseUrl = baseUrl;
        Configuration.remote = "https://" + login + ":" + password + "@" + remoteUrl;

        // for add Video to attach report
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screen");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
