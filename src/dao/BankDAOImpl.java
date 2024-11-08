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
	public void updateBank(Bank bank) throws SQLException {
		String sql = "UPDATE Bank SET bankName = ?, bankBranch = ? where bankId = ?";
		try (Connection con = DBConnection.getConnection();
		     PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, bank.getBankName());
			ps.setString(2, bank.getBankBranch());
			ps.setInt(3, bank.getBankId());
			int result = ps.executeUpdate();
			if (result == 0) {
				throw new SQLException("Update failed: No account found with the specified bank ID.");
			}
		}
	}
	
	
//	@Override    ---->  Bank cannot be deleted accounts will be deleted in the bank
//	public void deleteBank(int bankId) throws SQLException {
//		String sql = "DELETE FROM Bank WHERE bankId = ?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, bankId);
//            
//            int result = ps.executeUpdate();
//            if (result == 0) {
//                throw new SQLException("Delete failed: No account found with the specified account ID.");
//            }
//        }
//	}
	
	
	
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


	@Override
	public void deleteBank(int bankId) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
	
