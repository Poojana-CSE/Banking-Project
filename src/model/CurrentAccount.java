package model;

public class CurrentAccount extends Account{

	private double overdraftLimit;
	
	public CurrentAccount(int accountId, int customerId, Bank bankId, String accountType, double balance, double overdraftLimit) {
		super(accountId, customerId, bankId, accountType, balance);
		this.overdraftLimit = overdraftLimit;
	}

	
	//Getters And Setters
	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}


	@Override
	String getAccountDetails() {
		return "Current Account With OverDraft Limit: " + this.overdraftLimit;
	}


	@Override
	public String toString() {
		return "Current Account Details: "
				+ "Account Id: " + this.getAccountId() 
				+ "\nCustomer Id: " + this.getCustomerId() 
				+ "\nBank: "+ this.getBankId() 
				+ "\nAccount Type: " + this.getAccountType() 
				+ "\nBalance: " + this.getBalance()
				+ "\nOver Draft Limit: " + this.getOverdraftLimit();
	}
	
	
	
	
	
	
	
	
}
