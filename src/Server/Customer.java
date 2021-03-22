import java.util.ArrayList;

public class Customer {
	
	public ArrayList<Account> accounts;
	
	public Customer() {
		accounts = new ArrayList<>();
	}
	
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	public void addAccount(Account account) {
		accounts.add(account);		
	}

	public boolean checkDupeAccount(Account account){
		for (Account a : accounts){
			if (a.accountName.equals(account.accountName)){
				return true;
			}
		}
		return false;
	}
}
