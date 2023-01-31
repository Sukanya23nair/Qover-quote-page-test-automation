package com.qa.qover.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Sukanya
 *
 */
public class DriverFactory {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is:" + browserName);

		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
			break;

		default:
			System.out.println("pass the correct browser name:" + browserName);
			break;
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();

	}

	/**
	 * get driver using threadlocal
	 * 
	 * @return
	 */

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method will initialize properties from the properties file
	 * 
	 * @return prop
	 */

	public Properties init_prop() {
		Properties prop = null;
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * This method is used to take the screenshots and it will return the path of
	 * the screenshot
	 * 
	 * @return
	 */

	public static String getScreenShot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
