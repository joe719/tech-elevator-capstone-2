package com.techelevator.tenmo.models;

public class Transfer {
	
	private int transferId;
	private int transferTypeId;
	private String transferTypeName;
	private int transferStatusId;
	private String transferStatusName;
	private int accountFrom;
	private int accountTo;
	private double amount;
	private String senderUserName;
	private String recipientUserName;
	private int recipientuserId;
	private int senderUserId;
	
	
	@Override
	public String toString() {
	 return "ID: " + transferId + " To: " + recipientUserName + " From: " +
			 senderUserName + " Amount: " + amount;	
	}
	
	public String printTransferDetails(Transfer testTransfer) {
   	 return ("\n Id: " + transferId +
   			"\n From: " + senderUserName +
   			"\n To: " + recipientUserName +
			"\n Type: " + transferTypeName +
        	"\n Status: " + transferStatusName +
			"\n Amount: $" + amount);
   }
	
	
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getTransferTypeId() {
		return transferTypeId;
	}
	public void setTransferTypeId(int transferTypeId) {
		this.transferTypeId = transferTypeId;
	}
	public int getTransferStatusId() {
		return transferStatusId;
	}
	public void setTransferStatus(int transferStatusId) {
		this.transferStatusId = transferStatusId;
	}
	public int getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}
	public int getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getSenderUserName() {
		return senderUserName;
	}
	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}
	public String getRecipientUserName() {
		return recipientUserName;
	}
	public void setRecipientUserName(String recipientUserName) {
		this.recipientUserName = recipientUserName;
	}
	public int getRecipientuserId() {
		return recipientuserId;
	}
	public void setRecipientuserId(int recipientuserId) {
		this.recipientuserId = recipientuserId;
	}
	public int getSenderUserId() {
		return senderUserId;
	}
	public void setSenderUserId(int senderUserId) {
		this.senderUserId = senderUserId;
	}
	public String getTransferTypeName() {
		return transferTypeName;
	}
	public void setTransferTypeName(String transferTypeName) {
		this.transferTypeName = transferTypeName;
	}
	public String getTransferStatusName() {
		return transferStatusName;
	}
	public void setTransferStatusName(String transferStatusName) {
		this.transferStatusName = transferStatusName;
	}
	
	

}

