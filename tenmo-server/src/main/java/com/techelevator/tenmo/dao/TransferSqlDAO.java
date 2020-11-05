package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;



@Component
public class TransferSqlDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;
	
	  public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }
	
	
	@Override
	public List<Transfer> viewTransfersHistory(User user) {
	
		List <Transfer> transferHistory = new ArrayList();
		
		
        String sql = "SELECT account_from, account_to, amount " + 
        		"FROM transfers JOIN accounts on accounts.account_id = transfers.account_from " + 
        		"WHERE accounts.username = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user.getUsername());
        while(results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transferHistory.add(transfer);
		
        }
        return transferHistory;
	}

	
	
	
	
	
	@Override
	public List<Transfer> listTransfersByType(int transferType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transfer> listTransferByStatus(int transferStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setiD(rs.getLong("transfer_id"));
        transfer.setTransferType(rs.getInt("transfer_type_id"));
        transfer.setTransferStatus(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getDouble("amount"));
        return transfer;
    }
	

}
