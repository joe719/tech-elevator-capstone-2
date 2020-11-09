package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Account {
	
	@NotNull
	private long accountId;
	@NotNull
	private long userId;
	@Min(0)
	private double balance;
	private int senderUserId;
	private int recipientUserId;
	


	public Account () {}
	
	public Account (long accountId, long userId, double balance) {
		this.accountId = accountId;
		this.userId = userId;
		this.balance = balance;
	}
	
	
	
	public int getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(int senderUserId) {
		this.senderUserId = senderUserId;
	}

	public int getRecipientUserId() {
		return recipientUserId;
	}

	public void setRecipientUserId(int recipientUserId) {
		this.recipientUserId = recipientUserId;
	}
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	

}
