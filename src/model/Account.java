package model;

public abstract class Account {
	//make abstract because user dont need the implement part.
	//Eg: car driving - driven need not to know the mechanism of the acceralation, break.
	private int accountId;
	private int customerId;
	private Bank bankId;
	private String accountType;
	private double balance;
	//date automatically created by sql itself.
	
	//Constructor
	public Account(int accountId, int customerId, Bank bankId, String accountType, double balance) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.bankId = bankId;
		this.accountType = accountType;
		this.balance = balance;
	}
	
	abstract String getAccountDetails();
	
	
	//Getter And Setter.
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Bank getBankId() {
		return bankId;
	}
	public void setBankID(Bank bankId) {
		this.bankId = bankId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
		
}
