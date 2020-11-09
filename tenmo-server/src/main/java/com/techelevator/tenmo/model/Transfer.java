package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;

public class Transfer {


	private int transferId;
	@NotNull(message = "The field `transfer type id` should not be blank.")
	private int transferTypeId;
	private String transferTypeName;
	@NotNull(message = "The field `transfer status id` should not be blank.")
	private int transferStatusId;
	private String transferStatusName;
	@NotNull(message = "The field `account from` should not be blank.")
	private int accountFrom;
	@NotNull(message = "The field `account to` should not be blank.")
	private int accountTo;
	@NotNull(message = "The field `amount` should not be blank.")
	private double amount;
	private String senderUserName;
	private String recipientUserName;
	private int recipientuserId;
	private int senderUserId;
	
	

	public Transfer() {};
  
	
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

	public String getTransferTypeName() {
		return transferTypeName;
	}

	public void setTransferTypeName(String transferTypeName) {
		this.transferTypeName = transferTypeName;
	}

	public int getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(int transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public String getTransferStatusName() {
		return transferStatusName;
	}

	public void setTransferStatusName(String transferStatusName) {
		this.transferStatusName = transferStatusName;
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
}
	
