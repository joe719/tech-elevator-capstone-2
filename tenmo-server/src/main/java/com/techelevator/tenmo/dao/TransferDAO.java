package com.techelevator.tenmo.dao;

import java.security.Principal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;


public interface TransferDAO {

	List<Transfer> viewTransfersHistory(Principal principal);
	
	Transfer viewTransferDetailsByTransferId(int transferDetailId);

	void sendBucksCreatesNewTransfer(Transfer requestBucksNT); 
	
	public void requestBucks (Transfer requestBucks);
	
	public List<Transfer> viewPendingTransfers(Principal principal);


	
}
