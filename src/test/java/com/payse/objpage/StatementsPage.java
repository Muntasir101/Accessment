package com.payse.objpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class StatementsPage {

	WebDriver ldriver;

	public StatementsPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}


	@FindBy(how = How.NAME, using = "account_id")
	@CacheLookup
	WebElement name;

	@FindBy(how = How.NAME, using = "from_date")
	@CacheLookup
	WebElement dateFrom;


	@FindBy(how = How.NAME, using = "to_date")
	@CacheLookup
	WebElement dateTo;

	@FindBy(how = How.XPATH, using = "/html/body/div/form/button")
	@CacheLookup
	WebElement searchBtn;
	

	public void accountName(String aname) {
		name.sendKeys(aname);
		
	}

	public void statementFrom(String string) {
		dateFrom.sendKeys(string);

	}

	public void statementTo(String string) {
		dateTo.sendKeys(string);
		
	}

	public void search() {
		searchBtn.click();
		
	}

}
