package ru.moneta.pft.bugify.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    // fields
    private final Properties properties;
    private String browser;
    private WebDriver wd;
    private RestHelper restHelper;


    // constructor
    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();

    }

    // methods
    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public WebDriver getDriver() {
        if (wd == null){
            if (browser.equals(BrowserType.FIREFOX)){
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary(properties.getProperty("browser.firefox")));
                wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            } else if(browser.equals(BrowserType.GOOGLECHROME)){
                wd = new ChromeDriver();
                wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            } else if (browser.equals(BrowserType.IE)){
                wd = new InternetExplorerDriver();
                wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }
            wd.get(properties.getProperty("rest.url"));
        }
        return wd;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // helpers

    public RestHelper rest(){
        if (restHelper == null){
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }
}
