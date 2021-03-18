Feature: Automation Testing on Cash Flow Statement Suite App

Scenario: Test scenario for From date, To date & Currency dropdown field validations
Then Verify From date & To date field validations
Then Verify Currency dropdown in Cash Flow Statement

Scenario: Verification of Current balance field & Cash flow sublist
Then Verify Current balance field value & Cash flow sublist using excel data at "C:\Users\Sravan Kumar\Desktop\2021.1 SuiteApp Automation Data\Cash Flow Statement_DevProdRP.xlsx,Cash Flow Sublist"
Then Verify Final balance field value after selecting preferred transactions
Then Edit the Balance field & verify the alert
Then Verify Weekly forecast & download excel file
Then Verification of Cash flow sublist when no.of transactions are greater than five hundred