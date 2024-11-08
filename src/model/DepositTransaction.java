package model;

import java.util.Date;

public class DepositTransaction extends Transaction{
	
	private String depositMethod;

	public DepositTransaction(int transactionId, int accountId, String transactionType, double amount,
			Date transactionDate, String depositMethod) {
		super(transactionId, accountId, transactionType, amount, transactionDate);
		this.depositMethod = depositMethod;
	}

	public String getDepositMethod() {
		return depositMethod;
	}

	public void setDepositMethod(String depositMethod) {
		this.depositMethod = depositMethod;
	}

	@Override
	public String getTransactionDetails() {
		return "Transaction Type Is: " + depositMethod;
	}
	
	
	
}
