package newbank.server;

public class Account {
	
	private String accountName;
	private double openingBalance;
	private double currentBalance;
	private double amount;
	private String payee;


	//constructor
	public Account() {

	}


	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
	}



	public String toString() {
		return (accountName + ": " + openingBalance);
	}


	//account = new Account("Main", 100); // account object of the class Account

	// internal transfer out of account, update balance
	public void internalTransferOut(double Amount, String accountName){
		this.amount = Amount;
		this.accountName = accountName;
		currentBalance = openingBalance - amount;
	}

	// internal transfer into account, update balance
	public void internalTransferIn(double Amount, String accountName){
		this.amount = Amount;
		this.accountName = accountName;
		currentBalance = openingBalance + amount;
	}


	public double Transfer(double Amount, String From, String To){
		this.accountName = From;
		internalTransferOut(amount, From);
		this.accountName = To;
		internalTransferIn(amount, To);
		return currentBalance;

	}

	// set external payee
	private void setPayee(String Payee){
		this.payee = Payee;
	}
	// set amount for external payment
	private void setAmount(Double Amount){
		this.amount = Amount;
	}

	// make payment to external payee, deduct money transferred from account balance
	public void externalTransferOut(String accountName, String Payee, double Amount){

		this.accountName = accountName;
		setPayee(Payee);
		setAmount(Amount);
		currentBalance = openingBalance - amount;


	}






}
