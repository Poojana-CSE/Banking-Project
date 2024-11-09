package controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import exception.AccountNotFoundException;
import exception.BankingException;
import exception.InvalidAccountTypeException;
import model.Bank;
import model.CurrentAccount;
import model.SavingsAccount;
import service.AccountService;
import service.BankService;
import service.TransactionService;

public class BankController {
	
	private final AccountService accountService;
	private final BankService bankService;
	private final TransactionService transactionService;
	private final BufferedReader br;
	
	
	public BankController() {
		this.accountService = new AccountService();
		this.bankService = new BankService();
		this.transactionService = new TransactionService();
		this.br = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	public void start() throws NumberFormatException, IOException, SQLException, BankingException, InvalidAccountTypeException, ExecutionException, InterruptedException, AccountNotFoundException
	{
		boolean running = true;
		while(running) {
				displayMenu();
				int choice = Integer.parseInt(br.readLine());
				
				switch(choice) {
				case 1:
					createAccount();
					break;//fall through concept
					
					
				case 2:
					updateAccount();
					break;
					
					
				case 3:
					deleteAccount();
					break;
					
					
				case 4:
					viewAccount();
					break;
					
					
				case 5:
					updateBank();
					break;
					
				
				case 6:
					deposit();
					break;
					
				case 7:
					withdraw();
					break;
				
					
				case 8:
					transferFunds();
					break;
				case 0:
					running = false;
					transactionService.shutDownExecutorService();
					System.out.println("Exiting the app! GoodBye");
					break;
					
				}		
				
			}
	}
	
	

	

	public void displayMenu() {
		System.out.println("----------Banking Application-----------");
		System.out.println("1. Create Account");
		System.out.println("2. Update Account");
		System.out.println("3. Delete Account");
		System.out.println("4. View Account");
		System.out.println("5. Update Bank");
		System.out.println("6. Deposit");
		System.out.println("7. Withdraw");
		System.out.println("8. Transfer");
		System.out.println("0. Exit");
		System.out.println("Enter the choice: ");
	}
	
	
	public void createAccount() throws NumberFormatException, IOException, SQLException, BankingException, InvalidAccountTypeException {
		System.out.println("Enter the Customer ID: ");
		int cusId = Integer.parseInt(br.readLine());

		System.out.println("Enter the Bank ID: ");
		int bankId = Integer.parseInt(br.readLine());
		Bank bank = bankService.getBankById(bankId);
		
		System.out.println("Enter the Account Type(Savings/Current): ");
		String accountType = br.readLine();
		

		System.out.println("Enter the Initial Balance: ");
		double balance = Double.parseDouble(br.readLine());
		
		
		if("Savings".equalsIgnoreCase(accountType)) {
			System.out.println("Enter the Interest Rate: ");
			double interest = Double.parseDouble(br.readLine());
			accountService.createAccount(new SavingsAccount(0,cusId,bank,accountType,balance,interest));
		}
		
		else if("Current".equalsIgnoreCase(accountType)) {
			System.out.println("Enter the Over Draft Limit: ");
			double overdraftLimit = Double.parseDouble(br.readLine());
			accountService.createAccount(new CurrentAccount(0,cusId,bank,accountType,balance,overdraftLimit));
		}
		
		else {
			System.out.println("Invalid Account Type");
		}
		
	}
	
	
	
	
	public void updateAccount() throws SQLException, InvalidAccountTypeException, IOException, BankingException {
		System.out.println("Enter Account ID to update: ");
		int accountId = Integer.parseInt(br.readLine());
		System.out.println("Enter New Bank ID: ");
		int bankId = Integer.parseInt(br.readLine());
		Bank bank = bankService.getBankById(bankId);
		System.out.println("Enter New Account Type (Savings/Current): ");
		String accountType = br.readLine();
		System.out.println("Enter New Balance: ");
		double balance = Double.parseDouble(br.readLine());
		
		if ("Savings".equalsIgnoreCase(accountType)) {
			System.out.println("Enter New Interest Rate: ");
			double interestRate = Double.parseDouble(br.readLine());
			SavingsAccount updatedAccount = new SavingsAccount(accountId, 0, bank, accountType, balance, interestRate);
			accountService.updateAccount(updatedAccount);
		} 
		else if ("Current".equalsIgnoreCase(accountType)) {
			System.out.println("Enter New Overdraft Limit: ");
			double overdraftLimit = Double.parseDouble(br.readLine());
			CurrentAccount updatedAccount = new CurrentAccount(accountId, 0, bank, accountType, balance, overdraftLimit);
			accountService.updateAccount(updatedAccount);
		} 
		else {
			throw new InvalidAccountTypeException("Invalid account type provided.");
		}
		
		System.out.println("Account updated successfully.");
	}
	
	
	
	
	
	public void deleteAccount() throws SQLException, IOException {
	    System.out.println("Enter Account ID to delete: ");
	    int accountId = Integer.parseInt(br.readLine());

	    try {
	        accountService.deleteAccount(accountId);
	        System.out.println("Account deleted successfully.");
	    } catch (SQLException e) {
	        System.out.println("Failed to delete account: " + e.getMessage());
	    }
	}
	
	
	public void viewAccount() throws SQLException, NumberFormatException, IOException, AccountNotFoundException {
	    System.out.println("Enter Account ID to view: ");
	    int accountId = Integer.parseInt(br.readLine());

	    try {
	        accountService.viewAccount(accountId);
	        System.out.println("Account displayed successfully.");
	    } catch (SQLException e) {
	        System.out.println("Failed to display account: " + e.getMessage());
	    }
	}
	
	
	
	public void updateBank() throws NumberFormatException, IOException, SQLException
	{
		System.out.println("Enter the Bank ID: ");
		int bankId = Integer.parseInt(br.readLine());
		System.out.println("Enter the New Bank Name: ");
		String newBankName = br.readLine();
		System.out.println("Enter the New Bank Branch: ");
		String newBankBranch = br.readLine();
		
		Bank bank = new Bank(bankId,newBankName,newBankBranch);
		bankService.updateBank(bank);
		
		
	}
	
	
	//Bank Cant be deleted
//	public void deleteBank() throws NumberFormatException, IOException {
//		System.out.println("Enter Bank ID to delete: ");
//	    int bankId = Integer.parseInt(br.readLine());
//
//	    try {
//	        accountService.deleteAccount(bankId);
//	        System.out.println("Account deleted successfully.");
//	    } catch (SQLException e) {
//	        System.out.println("Failed to delete account: " + e.getMessage());
//	    }
//	}
	
	private void deposit() throws NumberFormatException, IOException, InterruptedException, ExecutionException {
		
		System.out.println("Enter Account Id: ");
		int id = Integer.parseInt(br.readLine());
		System.out.println("Enter The Amount To Be Deposited: ");
		double amount = Double.parseDouble(br.readLine());
		
		Future<?> future = transactionService.deposit(id, amount);
		future.get(); //Wait for deposit operation to complete 
		
	}
	
	
	private void withdraw() throws NumberFormatException, IOException, InterruptedException, ExecutionException {
		System.out.println("Enter Account Id: ");
		int id = Integer.parseInt(br.readLine());
		System.out.println("Enter The Amount To Be Withdrawn: ");
		double amount = Double.parseDouble(br.readLine());
		
		Future<?> future = transactionService.withdraw(id, amount);
		future.get(); //Wait for deposit operation to complete 	
	}
	
	
	private void transferFunds() throws IOException, InterruptedException, ExecutionException{
	       System.out.println("Enter  From Account id:");
	       int fromId= Integer.parseInt(br.readLine());
	        System.out.println("Enter  To Account id:");
	       int toId=Integer.parseInt(br.readLine());
	       System.out.println("Enter the amount to be transfered");
	       double amount=Double.parseDouble(br.readLine());
	       Future<?> future=transactionService.transferFunds(fromId,toId,amount);
	       future.get();
	   }
	
	
	
}//end of main class	
