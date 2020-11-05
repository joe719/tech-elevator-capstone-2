package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Transfer {

	private long iD;
	
	@NotNull(message = "The field `transfer type` should not be blank.")
	private int transferType;
	
	@NotNull(message = "The field `transfer status` should not be blank.")
	private int transferStatus;
	
	@NotNull(message = "The field `account from` should not be blank.")
	private int accountFrom;
	
	@NotNull(message = "The field `account to` should not be blank.")
	private int accountTo;
	
	@NotNull(message = "The field `amount` should not be blank.")
	private double amount;
	
	
	public Transfer() {};
	
	public Transfer(int transferType, int transferStatus, int accountFrom, int accountTo, double amount) {
		this.transferType = transferType;
		this.transferStatus = transferStatus;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
		
	}
	
	
	public Transfer(long iD, int transferType, int transferStatus, int accountFrom, int accountTo, double amount) {
		this.iD = iD;
		this.transferType = transferType;
		this.transferStatus = transferStatus;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
		
	}
	
	
	
	public long getiD() {
		return iD;
	}
	public void setiD(long iD) {
		this.iD = iD;
	}
	public int getTransferType() {
		return transferType;
	}
	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}
	public int getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(int transferStatus) {
		this.transferStatus = transferStatus;
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
	
	
	
}
