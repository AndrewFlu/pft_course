package ru.moneta.pft.addressbook.appmanager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    WebDriver wd;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();
        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary(properties.getProperty("browser.firefox")));
                wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            } else if (browser.equals(BrowserType.GOOGLECHROME)) {
                wd = new ChromeDriver();
                wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
                wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));


            if (browser.equals(BrowserType.FIREFOX)) {
                //wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary(properties.getProperty("browser.firefox")));

            } else if (browser.equals(BrowserType.CHROME)){
                wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);

            } else if (browser.equals(BrowserType.IE)) {
                wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
                wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }
        }

        wd.get(properties.getProperty("web.baseUrl"));

        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
    public DbHelper db(){ return dbHelper; }

    public byte[] takeScreenshot(){
        return ((TakesScreenshot)wd).getScreenshotAs(OutputType.BYTES);
    }


}
