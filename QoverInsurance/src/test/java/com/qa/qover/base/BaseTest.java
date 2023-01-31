package com.qa.qover.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.qa.qover.factory.DriverFactory;
import com.qa.qover.pages.GetQuotePage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	public Properties prop;
	public GetQuotePage getQuotePage;

	@BeforeMethod
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		driver.get(prop.getProperty("url"));
		getQuotePage = new GetQuotePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
