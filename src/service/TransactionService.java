package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dao.TransactionDAO;
import dao.TransactionDAOImpl;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.TransactionHistoryUtil;

public class TransactionService {
	private final TransactionDAO transactionDAO;
	private final ExecutorService executorService;
	
	public TransactionService() {
		this.transactionDAO = new TransactionDAOImpl();
		this.executorService = Executors.newFixedThreadPool(5);
	}
	
	
	public Future<?>deposit(int accountID,double amount){
	
		return executorService.submit(() -> {
			try {
				transactionDAO.deposit(accountID, amount);
				TransactionHistoryUtil.saveTransaction("Deposit", accountID, amount);
			}
			catch(SQLException | TransactionFailureException | InvalidTransactionAmountException | IOException e) {
				System.err.println("Error During Deposit: " + e.getMessage());
			}
		});//lambda function
	}
	
	
}
