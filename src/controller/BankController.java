package controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import exception.BankingException;
import exception.InvalidAccountTypeException;
import model.Bank;
import model.SavingsAccount;
import service.AccountService;
import service.BankService;

public class BankController {
	
	private final AccountService accountService;
	private final BankService bankService;
	private final BufferedReader br;
	
	
	public BankController() {
		this.accountService = new AccountService();
		this.bankService = new BankService();
		this.br = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	public void start() throws NumberFormatException, IOException, SQLException, BankingException, InvalidAccountTypeException
	{
		boolean running = true;
		while(running) {
				displayMenu();
				int choice = Integer.parseInt(br.readLine());
				
				switch(choice) {
				case 1:
					createAccount();
					break;//fall through concept
				}				
			}
	}
	
	public void displayMenu() {
		System.out.println("----------Banking Application-----------");
		System.out.println("1. Create Account");
//		System.out.println("2. Create Account");
//		System.out.println("3. Create Account");
//		System.out.println("4. Create Account");
//		System.out.println("5. Create Account");
//		System.out.println("6. Create Account");
//		System.out.println("7. Create Account");
//		System.out.println("8. Create Account");
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
		}
		
		else {
			System.out.println("Invalid Account Type");
		}
		
		
		
	}
	
	
	
	
	
	
}
