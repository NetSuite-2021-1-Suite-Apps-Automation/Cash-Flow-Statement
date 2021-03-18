package com.qa.cashFlowStatementRP.stepDefinition;

import java.text.ParseException;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;
import com.qa.cashFlowStatementRP.pages.AuthenticationPage;
import com.qa.cashFlowStatementRP.pages.CashFlowStatementPage;
import com.qa.cashFlowStatementRP.pages.HomePage;
import com.qa.cashFlowStatementRP.pages.LoginPage;
import com.qa.cashFlowStatementRP.util.ExcelDataToDataTable;
import com.qa.cashFlowStatementRP.util.TestBase;

import cucumber.api.DataTable;
import cucumber.api.Transform;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;

public class CashFlowStatementStepDefinition extends TestBase{
	
	LoginPage loginPage;
	AuthenticationPage authPage;
	HomePage homePage;
	CashFlowStatementPage cfPage;
	
	@After
	public void closeBrowser() {
		driver.quit();
	}
	
	@Then("^Verify From date & To date field validations$")
	public void verify_From_date_To_date_field_validations() throws InterruptedException {
		ExtentTest loginfo3 = null;
		try {
			test = extent.createTest("Verification of From date field validations");
			loginfo3 = test.createNode("Login Test");
			
			TestBase.init();
			loginPage = new LoginPage();
			authPage = loginPage.login();
			homePage = authPage.Authentication();
			homePage.changeRole(prop.getProperty("companyName"), prop.getProperty("role"), prop.getProperty("roleType"), prop.getProperty("selectedRole"));
			
			loginfo3.pass("Login Successful in Netsuite");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo3, e, "Login Test");
		}
		ExtentTest loginfo = null;
		try {
			loginfo = test.createNode("Verify From date field validations");
			cfPage = homePage.clickOnCashFlowStatementLink();
			cfPage.verifyFromDateFieldValidations(loginfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verify From date field validations");
		}
		ExtentTest loginfo2 = null;
		try {
			test = extent.createTest("Verification of To date field validations");
			loginfo2 = test.createNode("Verify To date field validations");
			cfPage.verifyToDateFieldValidations(loginfo2);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo2, e, "Verify From date field validations");
		}
	}
	
	@Then("^Verify Currency dropdown in Cash Flow Statement$")
	public void verify_currency_dropdown() throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Currency dropdown field validations");
			loginfo = test.createNode("Verification of Currency dropdown in Cash Flow Statement");
			cfPage.verifyCurrencyDropdownFieldValidation(loginfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of Currency dropdown in Cash Flow Statement");
		}
	}
	
	@Then("^Verify Current balance field value & Cash flow sublist using excel data at \"([^\"]*)\"$")
	public void verify_Current_balance_field_value_Cash_flow_sublist_using_excel_data_at(@Transform(ExcelDataToDataTable.class) DataTable cashFlowData) throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Current balance field value, Cash flow sublist data, Final balance field value");
			loginfo = test.createNode("Login Test");
			
			TestBase.init();
			loginPage = new LoginPage();
			authPage = loginPage.login();
			homePage = authPage.Authentication();
			homePage.changeRole(prop.getProperty("companyName"), prop.getProperty("role"), prop.getProperty("roleType"), prop.getProperty("selectedRole"));
			
			loginfo.pass("Login Successful in Netsuite");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Login Test");
		}
		ExtentTest loginfo1 = null;
		try {
			cfPage = new CashFlowStatementPage();
			for(Map<String,String> data: cashFlowData.asMaps(String.class, String.class)) {
				try {
					homePage.clickOnCashFlowStatementLink();
					String toDate = data.get("To Date");
					String currency = data.get("Currency");
					String cashAccounts = data.get("Cash Accounts");
					System.out.println(toDate+" "+currency+" "+cashAccounts);
					loginfo1 = test.createNode("Test Data >>> To Date: "+toDate+", Currency: "+currency+", Cash Accounts: "+cashAccounts);
					cfPage.verifyCurrentBalanceFieldValue(toDate, currency, cashAccounts, loginfo1);
					cfPage.verifyCashFlowSublist(toDate, currency, cashAccounts, loginfo1);
					cfPage.verifyBalanceFieldValues(loginfo1);
					cfPage.verifyFinalBalance(loginfo1);
				} catch (Exception e) {
					System.out.println("Cash Flow Statement failed for this data set.");
					loginfo1.fail("Cash Flow Statement failed for this data set.");
					loginfo1.fail(e.getMessage());
				}
			}
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo1, e, "Verification of Current balance field value, Cash flow sublist data, Final balance field value");
		}
	}
	
	@Then("^Verify Final balance field value after selecting preferred transactions$")
	public void verify_final_balance_on_selected_transactions() throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Final balance field value after selecting preferred transactions");
			loginfo = test.createNode("Verify Final balance field value");
			homePage.clickOnCashFlowStatementLink();
			cfPage.verifyFinalBalanceOnSelectedTransactions(loginfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of Final balance field value after selecting preferred transactions");
		}
	}
	
	@Then("^Edit the Balance field & verify the alert$")
	public void edit_the_Balance_field_verify_the_alert() throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of alert message while editing Balance field");
			loginfo = test.createNode("Verify the alert");
			cfPage.editBalanceFieldVerifyAlert(loginfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of alert message while editing Balance field");
		}
	}
	
	@Then("^Verify Weekly forecast & download excel file$")
	public void verify_Weekly_forecast() throws InterruptedException, ParseException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of Weekly forecast with due amounts");
			loginfo = test.createNode("Verify the Weekly forecast due amounts");
			homePage.clickOnCashFlowStatementLink();
			cfPage.verifyWeeklyForecast(loginfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of Weekly forecast with due amounts");
		}
		ExtentTest loginfo1 = null;
		try {
			test = extent.createTest("Verification of Download functionality in Cash Flow Statement");
			loginfo1 = test.createNode("Verify downloading the excel file");
			cfPage.downloadExcelFile(loginfo1);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo1, e, "Verification of Download functionality in Cash Flow Statement");
		}
	}
	
	@Then("^Verification of Cash flow sublist when no.of transactions are greater than five hundred$")
	public void verify_Cash_flow_sublist() throws InterruptedException {
		ExtentTest loginfo = null;
		try {
			test = extent.createTest("Verification of displayed results in the cash flow sublist when no.of transactions are greater than 500");
			loginfo = test.createNode("Verify the Cash flow sublist");
			homePage.clickOnCashFlowStatementLink();
			cfPage.verifyCfs(loginfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, loginfo, e, "Verification of displayed results in the cash flow sublist when no.of transactions are greater than 500");
		}
	}
}