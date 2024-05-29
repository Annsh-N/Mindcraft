package shopping.pojo;

public class Customer {
	private String username;
	private String password;
	private double wallet;
	
	public Customer() {
		
	}
	
	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		wallet = 0;
	}
	
	public Customer(String username, String password, double wallet) {
		super();
		this.username = username;
		this.password = password;
		this.wallet = wallet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	
	public void addBalance(double add) {
		wallet += add;
	}
}
