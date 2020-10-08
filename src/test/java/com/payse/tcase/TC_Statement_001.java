package com.payse.tcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.itextpdf.text.DocumentException;
import com.payse.objpage.StatementsPage;

/*
 * This is valid test case.
 * Should get one result.
 * Test should pass. 
 * After that 
 * 1. Screenshot and PDF will captured. 
 * 2. Test case status reported in Excel. 
 * Test data: Account ID: any test; Date From: 2020-01-01 00:00:00 ; Date To: 2020-10-07 00:00:000
 * 
 */
public class TC_Statement_001 extends BaseClass {

	@Test
	public void PyBankStatement() throws InterruptedException, IOException {

		driver.get(baseUrl);

		StatementsPage sp = new StatementsPage(driver);
		sp.accountName("any String");		
		Thread.sleep(1000);

		sp.statementFrom("2020-01-01 00:00:00");
		Thread.sleep(1000);		

		sp.statementTo("2020-10-07 00:00:00");
		Thread.sleep(1000);

		sp.search();
		Thread.sleep(1000);

		//-----
		String ExpectedAccount="any string";
		String ActualAccount=driver.findElement(By.xpath("//*[@id=\"account_id\"]")).getText();

		if(ExpectedAccount.equals(ActualAccount))
		{
			captureScreen(driver, "StatementPageTestPass");
			try {
				pdfCapture(driver,"StatementTestPass");
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ExcelImplement_Pass();
		}
		else
		{
			ExcelImplement_Fail();
			System.out.println("Statement not found");
		}
	}
}
