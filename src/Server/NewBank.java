import java.util.Arrays;
import java.util.HashMap;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;

	private NewBank() {
		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer();
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("Bhagy", bhagy);
		
		Customer christina = new Customer();
		christina.addAccount(new Account("Savings", 1500.0));
		customers.put("Christina", christina);
		
		Customer john = new Customer();
		john.addAccount(new Account("Checking", 250.0));
		customers.put("John", john);
	}
	
	public static NewBank getBank() {
		return bank;
	}
	
	public synchronized CustomerID checkLogInDetails(String userName, String password) {
		if(customers.containsKey(userName)) {
			return new CustomerID(userName);
		}
		return null;
	}

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(CustomerID customer, String request) {
		String[] splitted = new String[3];
		try {
			splitted = request.split(" ");
		}catch(ArrayIndexOutOfBoundsException e){
			splitted[0]=request;
		}
		if(customers.containsKey(customer.getKey())) {
			switch(splitted[0]) {
			case "SHOWMYACCOUNTS" : return showMyAccounts(customer);
			case "NEWACCOUNT" :
				if(splitted.length==2) {
					try{
						Double.parseDouble(splitted[1]);
						return"FAIL";
					}catch(NumberFormatException n){
						if(validAccType(splitted[1])) {
							Account account = new Account(splitted[1], 0);
							return newAccount(customer, account);
						}else{
							return"FAIL";
						}
					}
				}else if (splitted.length==3){
					double amount;
					try {
						amount = Double.parseDouble(splitted[2]);
					}catch(NumberFormatException n){
						return "FAIL";
					}
					Account account = new Account(splitted[1], amount);
					return newAccount(customer, account);
				}else{
					return "FAIL";
				}
			default : return "FAIL";
			}
		}
		return "FAIL";
	}
	
	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

	private String newAccount(CustomerID customer, Account account) {
		if (customers.get(customer.getKey()).checkDupeAccount(account)){
			return "FAIL";
		}
		customers.get(customer.getKey()).addAccount(account);
		return "SUCCESS";
	}

	private boolean validAccType(String str){

		if(Arrays.asList("Main","Current","Savings","Checking").contains(str)){
			return true;
		}
		return false;
	}
}
