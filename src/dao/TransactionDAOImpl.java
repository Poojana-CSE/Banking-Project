package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.DBConnection;

public class TransactionDAOImpl implements TransactionDAO{

	@Override
	public synchronized void deposit(int accountId, double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException {
		
		if(amount <= 0) {
			throw new InvalidTransactionAmountException("Deposit Amount Must Be Greater Than 0");
		}
		
		try(Connection con = DBConnection.getConnection()){
			CallableStatement st = con.prepareCall("{CALL depositFunds(?,?)}");
			st.setInt(1, accountId);
			st.setDouble(2, amount);
			st.execute();
		} catch(SQLException e) {
			throw new TransactionFailureException("Deposit Failed: " + e.getMessage());
		}
		
		
	}

	@Override
	public synchronized void withdraw(int accountId, double amount) {
		
		
	}

	@Override
	public synchronized void transferFunds(int fromAccount, int toAccount, double amount) {
		
		
	}

}
