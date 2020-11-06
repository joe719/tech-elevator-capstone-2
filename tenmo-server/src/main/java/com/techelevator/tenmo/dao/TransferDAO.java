package com.techelevator.tenmo.dao;

import java.security.Principal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

public interface TransferDAO {

	List<Transfer> viewTransfersHistory(Principal principal);
	
	List<Transfer> listTransfersByType(int transferType);
	
	List<Transfer> listTransferByStatus(int transferStatus);
	
}
