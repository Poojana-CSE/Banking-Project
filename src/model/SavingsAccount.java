package model;

public class SavingsAccount extends Account {
	
	private double interestRate;
	
	public SavingsAccount(int accountId, int customerId, Bank bankId, String accountType, double balance, double interestRate) {
		super(accountId, customerId, bankId, accountType, balance);
		this.interestRate = interestRate;
	}

	//Getters And Setters
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String getAccountDetails() {
		return "Savings Account With Interest Rate: " + this.interestRate;
	}
	
	
	@Override
	public String toString() {
		return "Savings Account Details: "
				+ "Account Id: " + this.getAccountId() 
				+ "\nCustomer Id: " + this.getCustomerId() 
				+ "\nBank: "+ this.getBankId() 
				+ "\nAccount Type: " + this.getAccountType() 
				+ "\nBalance: " + this.getBalance()
				+ "\nInterest Rate: " + this.getInterestRate();
	}
	
	
}
