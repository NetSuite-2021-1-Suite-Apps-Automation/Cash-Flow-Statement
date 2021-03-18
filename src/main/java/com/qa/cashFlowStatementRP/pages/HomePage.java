package com.qa.cashFlowStatementRP.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.cashFlowStatementRP.util.TestBase;

public class HomePage extends TestBase{
	@FindBy(xpath = "//li[contains(@id,'ns-header-menu-userrole-item')]/a/span[@class='ns-role-menuitem-text']")
	List<WebElement> rolesList;
	
	@FindBy(xpath = "//span[text()='Transactions']")
	WebElement transactionsLink;
	
	@FindBy(xpath = "//a[contains(@class,'ns-scroll-button-bottom')]")
	WebElement scrollBtn;
	
	@FindBy(xpath = "//span[text()='Cash Flow Statement']")
	WebElement cashFlowStatementLink1;
	
	@FindBy(xpath = "//li[@class='ns-menuitem ']//span[text()='Cash Flow Statement']")
	WebElement cashFlowStatementLink2;
	
	@FindBy(xpath = "//div[@class='ns-role']/span[2]")
	WebElement role;
	
	@FindBy(xpath = "//div[@id='devpgloadtime']//following-sibling::div[@class='ns-logo']//img")
	List<WebElement> images;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public CashFlowStatementPage clickOnCashFlowStatementLink() throws InterruptedException{
		action = new Actions(driver);
		eleAvailability(driver, By.xpath("//span[text()='Transactions']"), 5);
		action.moveToElement(transactionsLink).build().perform();
		try {
			eleAvailability(driver, By.xpath("//span[text()='Cash Flow Statement']"), 5); // Explicit Wait
		} catch (Exception e) {
			eleAvailability(driver, scrollBtn, 5);
			action.moveToElement(scrollBtn).build().perform();
			Thread.sleep(1500);
			eleAvailability(driver, cashFlowStatementLink1, 5);
		}
		action.moveToElement(cashFlowStatementLink1).build().perform();
		eleAvailability(driver, By.xpath("//li[@class='ns-menuitem ']//span[text()='Cash Flow Statement']"), 5); // Explicit Wait
		cashFlowStatementLink2.click();
		
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
		}
		
		return new CashFlowStatementPage();
	}
	
	public void changeRole(String company, String roleData, String roleType, String selectedRole) {
		action = new Actions(driver);
		String currentRole = role.getText().trim();
		if(currentRole.equals(selectedRole)) {
			System.out.println("Role already selected");
		} else {
			action.moveToElement(driver.findElement(By.xpath("//div[@id='spn_cRR_d1']/a"))).build().perform();
			for(int i=0;i<rolesList.size();i++) {
				WebElement roleElement = rolesList.get(i);
				String roleValue = roleElement.getText().trim();
				if(roleValue.contains(company) && roleValue.contains(roleData) && roleValue.contains(roleType)) {
					roleElement.click();
					break;
				}
			}
		}
		
	}
}
