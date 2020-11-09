package com.techelevator.tenmo.models;

public class Account {
	
	private long accountId;
	private long userId;
	private double balance;
	private int senderUserId;
	private int recipientUserId;
	
	


	public Account (double balance) {
		this.balance = balance;
		
	}
	
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
