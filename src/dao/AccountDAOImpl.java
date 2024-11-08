package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.InvalidAccountTypeException;
import model.Account;
import utility.DBConnection;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {
		
		
		
		String sql = "Insert into Account(customerId,bankId,accountType,balance) values(?,?,?,?)";
		
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, account.getCustomerId());
			ps.setInt(2, account.getBankId().getBankId());
			ps.setString(3, account.getAccountType());
			ps.setDouble(4, account.getBalance());
			
			int result = ps.executeUpdate();
			if(result == 0) {
				throw new InvalidAccountTypeException("" + "Account Type Not Recognized");
				
			}
			
		}
		
		
		
	}
	
}
