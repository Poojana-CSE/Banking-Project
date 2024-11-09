package dao;

import java.sql.SQLException;

import exception.AccountNotFoundException;
import exception.InvalidAccountTypeException;
import model.Account;

public interface AccountDAO {
	
	void createAccount(Account account) throws SQLException, InvalidAccountTypeException;
	void updateAccount(Account account) throws SQLException, InvalidAccountTypeException;
	void deleteAccount(int accountId) throws SQLException;
	void viewAccount(int accountId) throws SQLException, AccountNotFoundException;
}
