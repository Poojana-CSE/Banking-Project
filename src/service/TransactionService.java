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
	
	
	public Future<?> deposit(int accountId,double amount){
	
		return executorService.submit(() -> {
			try {
				transactionDAO.deposit(accountId, amount);
				TransactionHistoryUtil.saveTransaction("Deposit", accountId, amount);
			}
			catch(SQLException | TransactionFailureException | InvalidTransactionAmountException | IOException e) {
				System.err.println("Error During Deposit: " + e.getMessage());
			}
		});//lambda function
	}
	
	public Future<?> withdraw(int accountId,double amount){
		return executorService.submit(() -> {
			try {
				transactionDAO.withdraw(accountId, amount);
				TransactionHistoryUtil.saveTransaction("WithDraw", accountId, amount);
			}
			catch(TransactionFailureException | InvalidTransactionAmountException | IOException e) {
				System.err.println("Error During Withdraw: " + e.getMessage());
			}
		});
	}
	
	
	


	public void shutDownExecutorService() {
		executorService.shutdown();
		
	}


public Future<?>transferFunds(int fromAccount,int toAccount,double amount){
	    
        return executorService.submit(()->{
            try{
            transactionDAO.transferFunds(fromAccount,toAccount, amount);
            TransactionHistoryUtil.saveTransaction("Transfer Funds",fromAccount,toAccount,amount);
            }
            catch(IOException e){
                System.err.println("Error during withdraw:"+e.getMessage());
            } catch (InvalidTransactionAmountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionFailureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
	
	
	
	
}
