package service;

import java.sql.SQLException;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import exception.InvalidAccountTypeException;
import model.Account;

public class AccountService {
	
	private final AccountDAO accountDAO;
	
	public AccountService() {
		this.accountDAO = new AccountDAOImpl();
	}
	
	public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {
		accountDAO.createAccount(account);
	}
	
	public void updateAccount(Account account) throws SQLException, InvalidAccountTypeException {
		accountDAO.updateAccount(account);
	}
	
	public void deleteAccount(int accountId) throws SQLException {
		accountDAO.deleteAccount(accountId);
	}
	
}
