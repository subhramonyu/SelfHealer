package org.client.ui.tests;

import org.client.factory.core.BaseTest;
import org.client.ui.pages.CalculatorPage;
import org.client.ui.utils.TestGroup;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CalculatorTest extends BaseTest {
	
	
private CalculatorPage calculator;

	
	@BeforeClass(groups = { TestGroup.CALCULATOR_TEST})
	public void initClass() {
		calculator = new CalculatorPage();
	}
	
	
	@Test(groups = {TestGroup.CALCULATOR_TEST }, priority = 0001)
	@Parameters({"operation","num1","num2"})
	public void calculatorTest(String operation , String num1 ,String num2) throws Exception {
		Assert.assertEquals(Integer.valueOf(num1)+ Integer.valueOf(num2) , calculator.add(num1, num2));	
	}

}
