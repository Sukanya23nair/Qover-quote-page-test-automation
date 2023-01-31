package com.qa.qover.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.qover.utils.Constants;
import com.qa.qover.utils.ElementUtil;

public class GetQuotePage {
	private ElementUtil elementUtil;

	/*
	 * private page locators
	 */

//Language locator
	private By language = By.xpath("//select[@class='small filled']");

//bike Type Dropdown locator
	private By bikeTypeDropdown = By.xpath("//select[@id='bike.quote.type']");

//bike Type DropdownError locator
	private By bikeTypeDropdownError = By
			.xpath("//select[@data-test='bike.quote.type']/../../div[@data-test='error.required']/span");

// insured value TextField 
	private By insuredvalueTxtField = By.id("bike.quote.originalValue");

//Error message text locator for(below minimum cutoff value) 
	private By minmum_InsuredvalueErr = By.xpath("//div[@data-test='error.minValue']/span");

//Error message text locator for(above max cutoff value) 
	private By max_insuredValueError = By.xpath("//div[@data-test='error.maxValue']/span");

//Bike dropdown locator
	private By bikeLocator = By.id("bike.quote.antiTheftMeasure");

//See price button locator
	private By SeePriceButton = By.cssSelector(".sc-1yccttm-0.jNvFvF.justify");

//Choose plan for Theft + Assistance Plan locator
	private By choosetheftPlusAssistancePlan = By
			.xpath("//button[@data-test='bike.quote.card.VARIANT_THEFT_ASSISTANCE']");

//Choose plan for omnium plan locator
	private By chooseomniumPlanLocator = By
			.xpath("//button[@data-test='bike.quote.card.VARIANT_THEFT_DAMAGE_ASSISTANCE']");

//locator to verify Omnium Quote generation
	private By toVerify_OmniumQuote = By
			.xpath("//div[@class='sc-oehnoy-0 cuafDz animated fadeIn text-center']/span[text()='To be paid annually']");

//locator to verify Theft + Assistance Quote generation
	private By toVerify_Theft = By
			.xpath("//div[@class='sc-oehnoy-0 cuafDz animated fadeIn text-center']/span[text()='To be paid annually']");

//page constructor
	public GetQuotePage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}

	/*
	 * public page actions
	 */

	/**
	 * Method returning get quote page title Method
	 * 
	 * @return
	 */
	public String getQuotePageTitle() {
		return elementUtil.waitForTitleToBe(Constants.TITLE, 5);
	}

	/**
	 * Method returning Error message for Bike type dropdown
	 * 
	 * @param insuredValue
	 * @param bike
	 * @return
	 */
	public String errorMessageBikeTypeDropdown(String insuredValue, String bike) {
		elementUtil.doSelectByVisibleText(language, Constants.LANG);
		elementUtil.doSendKeys(insuredvalueTxtField, insuredValue);
		elementUtil.doSelectByVisibleText(bikeLocator, bike);
		elementUtil.doClick(SeePriceButton);
		return elementUtil.doGetText(bikeTypeDropdownError);
	}

	/**
	 * Method returning error message for minimum value cutoff for Insured Value
	 * Field
	 * 
	 * @param biketype
	 * @param MinimuminsuredValue
	 * @param bike
	 * @return
	 */
	public String minimumValueErrorMessage_InsuredValueField(String biketype, String MinimuminsuredValue, String bike) {
		elementUtil.doSelectByVisibleText(language, Constants.LANG);
		elementUtil.doSelectByVisibleText(bikeTypeDropdown, biketype);
		elementUtil.doSendKeys(insuredvalueTxtField, MinimuminsuredValue);
		elementUtil.doSelectByVisibleText(bikeLocator, bike);
		elementUtil.doClick(SeePriceButton);
		return elementUtil.doGetText(minmum_InsuredvalueErr);
	}

	/**
	 * Method returning error message for maximum value cutoff for Insured Value
	 * Field
	 * 
	 * @param biketype
	 * @param Max_insuredValue
	 * @param bike
	 * @return
	 */
	public String maxValueErrorMessage_InsuredValueField(String biketype, String Max_insuredValue, String bike) {
		elementUtil.doSelectByVisibleText(language, Constants.LANG);
		elementUtil.doSelectByVisibleText(bikeTypeDropdown, biketype);
		elementUtil.doSendKeys(insuredvalueTxtField, Max_insuredValue);
		elementUtil.doSelectByVisibleText(bikeLocator, bike);
		elementUtil.doClick(SeePriceButton);
		return elementUtil.doGetText(max_insuredValueError);
	}

	/**
	 * selecting THEFT + ASSISTANCE plan
	 * 
	 * @param biketype
	 * @param insuredValue
	 * @param bike
	 * @return
	 */
	public String quotGeneration_TheftPlusAssistancePlan(String biketype, String insuredValue, String bike) {
		elementUtil.doSelectByVisibleText(language, Constants.LANG);
		elementUtil.doSelectByVisibleText(bikeTypeDropdown, biketype);
		elementUtil.doSendKeys(insuredvalueTxtField, insuredValue);
		elementUtil.doSelectByVisibleText(bikeLocator, bike);
		elementUtil.doClick(SeePriceButton);
		elementUtil.clickWhenElementReady(choosetheftPlusAssistancePlan, 5).click();
		return elementUtil.doGetText(toVerify_Theft);
	}

	/**
	 * selecting Omnium plan
	 * 
	 * @param biketype
	 * @param insuredValue
	 * @param bike
	 * @return
	 */
	public String quotGeneration_Omnium(String biketype, String insuredValue, String bike) {
		elementUtil.doSelectByVisibleText(language, Constants.LANG);
		elementUtil.doSelectByVisibleText(bikeTypeDropdown, biketype);
		elementUtil.doSendKeys(insuredvalueTxtField, insuredValue);
		elementUtil.doSelectByVisibleText(bikeLocator, bike);
		elementUtil.doClick(SeePriceButton);
		elementUtil.clickWhenElementReady(chooseomniumPlanLocator, 5).click();
		return elementUtil.doGetText(toVerify_OmniumQuote);
	}

}
