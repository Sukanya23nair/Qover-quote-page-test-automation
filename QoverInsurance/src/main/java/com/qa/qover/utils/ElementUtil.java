package com.qa.qover.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	// Constructer
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * wrapper method of findElement
	 * 
	 * @param locator
	 * @return Webelement
	 */
	public WebElement getElement(By locator) {
		return driver.findElement(locator);

	}

	/**
	 * wrapper method of findElements
	 * 
	 * @param locator
	 * @return Webelement
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	/**
	 * wrapper method of sendKeys
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	/**
	 * wrapper method of click
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * wrapper method of getText
	 * 
	 * @param locator
	 * @return String
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * Dropdown Method
	 * 
	 * @param locator
	 * @param text
	 */

	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	/**
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * Webdriver wait for click
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement clickWhenElementReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * To get title
	 * 
	 * @param title
	 * @param timeOut
	 * @return
	 */
	public String waitForTitleToBe(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

}
