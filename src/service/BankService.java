package service;

import java.sql.SQLException;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import dao.BankDAO;
import dao.BankDAOImpl;
import exception.BankingException;
import model.Bank;

public class BankService {
	private final BankDAO bankDAO;
	private final AccountDAO accountDAO;
	public BankService() {
		this.bankDAO = new BankDAOImpl();
		this.accountDAO = new AccountDAOImpl();
	}
	public Bank getBankById(int bankId) throws SQLException,BankingException{
		return bankDAO.getBankById(bankId);
	}
	
	public void updateBank(Bank bank) throws SQLException {
		bankDAO.updateBank(bank);
	}
	
//	public void deleteBank(int bankID) throws SQLException {
//		bankDAO.deleteBank(bankID);
//	}

}