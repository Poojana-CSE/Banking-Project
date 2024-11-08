package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import exception.BankingException;
import model.Bank;
import utility.DBConnection;

public class BankDAOImpl implements BankDAO {
	
	
	@Override
	public Bank getBankById(int bankId) throws SQLException, BankingException {
		String sql = "Select * from Bank where bankId = ?";
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setInt(1, bankId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Bank(bankId,rs.getString("bankName"),rs.getString("bankBranch"));
			}
			throw new BankingException("Bank Id Not Recognized: " + bankId);
		}
	}
}
	
