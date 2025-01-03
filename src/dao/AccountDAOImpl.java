package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.AccountNotFoundException;
import exception.InvalidAccountTypeException;
import model.Account;
import utility.DBConnection;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {
		
		String sql1 = "Insert into Account(customerId,bankId,accountType,balance) values(?,?,?,?)";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql1))
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
		
		@Override
		public void updateAccount(Account account) throws SQLException {
			String sql = "UPDATE Account SET bankId = ?, accountType = ?, balance = ? WHERE accountId = ?";
			try (Connection con = DBConnection.getConnection();
			     PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, account.getBankId().getBankId());
				ps.setString(2, account.getAccountType());
				ps.setDouble(3, account.getBalance());
				ps.setInt(4, account.getAccountId());
				int result = ps.executeUpdate();
				if (result == 0) {
					throw new SQLException("Update failed: No account found with the specified account ID.");
				}
			}
		}
		
		@Override
		public void deleteAccount(int accountId) throws SQLException {
	        String sql = "DELETE FROM Account WHERE accountId = ?";
	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, accountId);
	            
	            int result = ps.executeUpdate();
	            if (result == 0) {
	                throw new SQLException("Delete failed: No account found with the specified account ID.");
	            }
	        }
	    }

		
		public void viewAccount(int accountId) throws SQLException, AccountNotFoundException {
			String sql="select * from Account where accountId = ?";
			try(Connection con = DBConnection.getConnection();
					PreparedStatement ps=con.prepareStatement(sql))
			{
				ps.setInt(1, accountId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					System.out.println("Account ID: "+rs.getString("accountId")+"\n");
					System.out.println("Customer ID: "+rs.getString("customerId")+"\n");
					System.out.println("Bank ID: "+rs.getString("bankId")+"\n");
					System.out.println("Account Type: "+rs.getString("accountType")+"\n");
					System.out.println("Balance: "+rs.getString("balance")+"\n");
					
				}else {
					throw new AccountNotFoundException(""+"Account ID Invalid");
					
				}
			}
			
		}

}
