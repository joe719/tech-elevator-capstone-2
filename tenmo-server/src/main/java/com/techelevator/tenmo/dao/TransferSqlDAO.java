package com.techelevator.tenmo.dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;




@Component
public class TransferSqlDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;
	
	  public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }
	
	
	@Override
	public List<Transfer> viewTransfersHistory(Principal principal) {
	
		List <Transfer> transferHistory = new ArrayList<>();
		
		
        String sql = "SELECT transfer_id, amount, " +

        		"(SELECT username AS recipient " +
        		"FROM users JOIN accounts USING (user_id) " +
        		"WHERE account_id = (SELECT account_to FROM transfers " +
        		"FULL OUTER JOIN accounts ON transfers.account_from = accounts.account_id " +
        		"FULL OUTER JOIN users ON users.user_id = accounts.user_id WHERE transfer_id = 1)), " +

        		"(SELECT username AS sender " +
        		"FROM users JOIN accounts USING (user_id) " +
        		"WHERE account_id = (SELECT account_from FROM transfers " +
        		"FULL OUTER JOIN accounts ON transfers.account_to = accounts.account_id " +
        		"FULL OUTER JOIN users ON users.user_id = accounts.user_id WHERE transfer_id = 1)) " +

        		"FROM transfers JOIN accounts on accounts.account_id = transfers.account_from " +
        		"JOIN users USING (user_id) " +
        		"WHERE users.username =  ?";
        
        
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName());
        while(results.next()) {
            Transfer transfer = mapRowForViewTransferHistory(results);
            transferHistory.add(transfer);
		
        }
        return transferHistory;
	}

	
	@Override

	public Transfer viewTransferDetailsByTransferId(Transfer transferDetails) {

		String sql = "SELECT transfers.transfer_id, (SELECT username AS sender\n" + 

				"                                FROM users JOIN accounts USING (user_id)\n" + 

				"                                WHERE account_id = (SELECT account_from FROM transfers\n" + 

				"                                FULL OUTER JOIN accounts ON transfers.account_to = accounts.account_id\n" + 

				"                                FULL OUTER JOIN users ON users.user_id = accounts.user_id WHERE transfer_id = ?)),\n" + 

				"\n" + 

				"                               (SELECT username AS recipient\n" + 

				"                               FROM users JOIN accounts USING (user_id)\n" + 

				"                               WHERE account_id = (SELECT account_to FROM transfers\n" + 

				"                               FULL OUTER JOIN accounts ON transfers.account_from = accounts.account_id\n" + 

				"                               FULL OUTER JOIN users ON users.user_id = accounts.user_id WHERE transfer_id = ?)), transfer_types.transfer_type_desc , transfer_statuses.transfer_status_desc, transfers.amount\n" + 

				"FROM transfers \n" + 

				"JOIN accounts ON accounts.account_id = transfers.account_from\n" + 

				"JOIN users ON accounts.user_id = users.user_id\n" + 

				"JOIN transfer_types ON transfers.transfer_type_id = transfer_types.transfer_type_id\n" + 

				"JOIN transfer_statuses ON transfers.transfer_status_id = transfer_statuses.transfer_status_id\n" + 

				"WHERE transfers.transfer_id = ?";

		

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferDetails.getTransferId(), transferDetails.getTransferId(), transferDetails.getTransferId());

		

		if(rowSet.next()) {

			transferDetails = mapRowToTransferDetails(rowSet);

			

		}else

			return null;

		

		return transferDetails;

	}
	
	
	
	
	
	
	
	
	
	
	

		
	
	
	
	@Override
	public void sendBucksCreatesNewTransfer(Transfer requestBucksNT) {
		String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " + 
				"VALUES (DEFAULT, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, requestBucksNT.getTransferTypeId(), requestBucksNT.getTransferStatusId(), requestBucksNT.getAccountFrom(), requestBucksNT.getAccountTo(), requestBucksNT.getAmount());
		
		
	}

	
	@Override
	public void sendUpdatesUserBalance (Transfer sendBucksUpdates) {
	 
		String sql = "UPDATE accounts SET balance = balance - ? " +
					 "FROM users WHERE accounts.user_id = users.user_id AND users.username = ?";
		jdbcTemplate.update(sql, sendBucksUpdates.getAmount(), sendBucksUpdates.getSenderUserName());
		
		String sql2 = "UPDATE accounts SET balance = balance + ? " +
				 	"FROM users WHERE accounts.user_id = users.user_id AND users.user_id = ?";
		jdbcTemplate.update(sql2, sendBucksUpdates.getAmount(), sendBucksUpdates.getRecipientuserId());
		
	
	}
	
	
	public void requestBucks (Transfer requestBucks) {
		
		String sql =  "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, " +
				"account_from, account_to, amount) VALUES (DEFAULT, 1, 1, ?, ?, ?) ";
		jdbcTemplate.update(sql, requestBucks.getAccountFrom(), requestBucks.getAccountTo(), 
				requestBucks.getAmount());
	
	}

	

	
    private Transfer mapRowForViewTransferHistory(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setAmount(rs.getDouble("amount"));
        transfer.setRecipientUserName(rs.getString("recipient"));
        transfer.setSenderUserName(rs.getString("sender"));
   
        return transfer;
    }
	
    private Transfer mapRowToTransferDetails(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setSenderUserName(rs.getString("sender"));
        transfer.setRecipientUserName(rs.getString("recipient"));
        transfer.setTransferTypeName(rs.getString("transfer_type_desc"));
        transfer.setTransferStatusName(rs.getString("transfer_status_desc"));
        transfer.setAmount(rs.getDouble("amount"));
    
        return transfer;
    }

}
