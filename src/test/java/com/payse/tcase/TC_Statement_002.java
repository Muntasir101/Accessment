package com.payse.tcase;

import java.io.IOException;

import org.testng.annotations.Test;

import com.itextpdf.text.DocumentException;
import com.payse.objpage.StatementsPage;

/*
 * This is valid test case. Exact same time when transition.
 * Should get one result.If test fail generate Bug report in pdf format. 
 * Test should pass. 
 * After that 
 * 1. Screenshot and PDF will captured. 
 * 2. Test case status reported in Excel. 
 * Test data: Account ID: any test; Date From: 2020-01-01 00:00:00 ; Date To: 2020-10-07 00:00:000
 * 
 */

public class TC_Statement_002 extends BaseClass {

	@Test
	public void PyBankStatement() throws InterruptedException, IOException {

		driver.get(baseUrl);

		StatementsPage sp = new StatementsPage(driver);
		sp.accountName("any String");		
		Thread.sleep(1000);

		sp.statementFrom("2020-08-21 13:30:17");
		Thread.sleep(1000);		

		sp.statementTo("2020-08-21 13:30:17");
		Thread.sleep(1000);

		sp.search();
		Thread.sleep(1000);

		//-----
		boolean ExpectedElement=driver.getPageSource().contains("10.00 EUR");

		//String ActualAccount=driver.findElement(By.xpath("//*[@id=\"account_id\"]")).getText();

		if(!ExpectedElement)
		{
			captureScreen(driver, "BugScreenshot");
			try {
				pdfCapture(driver,"BugReportStatement");
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			ExcelImplement_Fail();
		}
		else
		{
			ExcelImplement_Pass();
			System.out.println("Statement found");
		}
	}
}
