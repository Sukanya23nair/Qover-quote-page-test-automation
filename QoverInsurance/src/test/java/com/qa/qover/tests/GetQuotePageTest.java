/**
 * 1st output: A real implementation for a specific page/field of your choice
 * @author Sukanya
 * 31 jan 2023 10 PM CET
 */
package com.qa.qover.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.qover.base.BaseTest;
import com.qa.qover.utils.Constants;

public class GetQuotePageTest extends BaseTest {

	/**
	 * Quote Page title verification
	 */
	@Test
	public void verifyGetQuotePageTitle() {
		String title = getQuotePage.getQuotePageTitle();// Actuall title
		System.out.println("Login page title is:" + title);
		Assert.assertEquals(title, Constants.TITLE); // Comparing Actual title with Expected title values using
														// Assertion
	}

	/**
	 * Verify error message of bike type drop down
	 */
	@Test
	public void VerifyerrorMessage_BikeTypeDropdown() {
		String bikeTypeDropdownErrorTxt = getQuotePage.errorMessageBikeTypeDropdown(Constants.INSURED_VALUE,
				Constants.BIKE);
		Assert.assertEquals(bikeTypeDropdownErrorTxt, Constants.NO_INPUT_ERROR);
	}

	/**
	 * Verify error message of insured value field for minimum input value(with
	 * minimum cut of value 250)
	 */
	@Test
	public void VerifyErrorMessage_InsuredValueFieldMinimumIP() {
		String InsuredValueField_ErrorNoInputTxt_MinimValue = getQuotePage.minimumValueErrorMessage_InsuredValueField(
				Constants.BIKE_TYPE1, Constants.MINIMUM_INSURED_VALUE, Constants.BIKE);
		Assert.assertEquals(InsuredValueField_ErrorNoInputTxt_MinimValue, Constants.MINIMUM_INSURED_VALUE_ERR_MSG);
	}

	/**
	 * Verify error message of insured value field for maximum input value(with
	 * maximum value 10000)
	 */
	@Test
	public void VerifyErrorMessage_InsuredValueFieldMaximumIP() {
		String InsuredValueFieldErrorNoInputTxt_maxValue = getQuotePage.maxValueErrorMessage_InsuredValueField(
				Constants.BIKE_TYPE1, Constants.MAXIMUM_INSURED_VALUE, Constants.BIKE);
		Assert.assertEquals(InsuredValueFieldErrorNoInputTxt_maxValue, Constants.MAXIMUM_INSURED_VALUE_ERR_MSG);
	}

	/**
	 * Test data for VerifyquotGeneration_Omnium method and
	 * VerifyquotGeneration_TheftandAssistance
	 * 
	 * @return
	 */
	@DataProvider
	public Object[][] testData() {
		return new Object[][] {

				{ "Racing bike", "450", "Equipped without GPS tracker" },
				{ "Mountainbike", "10000", "Equipped without GPS tracker" },

		};
	}

	/**
	 * Method Verifying quote generation for Omnium plan
	 * 
	 * @param biketype
	 * @param insuredValue
	 * @param bike
	 */
	@Test(dataProvider = "testData")
	public void VerifyquotGeneration_Omnium(String biketype, String insuredValue, String bike) {
		String txt = getQuotePage.quotGeneration_Omnium(biketype, insuredValue, bike);
		Assert.assertEquals(txt, Constants.OMNIPLAN_HEADER);
	}

	/**
	 * Method Verifying quote generation for Theft + Assistance
	 * 
	 * @param biketype
	 * @param insuredValue
	 * @param bike
	 */
	@Test(dataProvider = "testData")
	public void VerifyquotGeneration_TheftandAssistance(String biketype, String insuredValue, String bike) {
		String txt = getQuotePage.quotGeneration_TheftPlusAssistancePlan(biketype, insuredValue, bike);
		Assert.assertEquals(txt, Constants.THEFT_HEADER);

	}

}
