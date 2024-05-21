package inheritance;

public class Date {
	private String date;
	
	public Date(String date) {
		this.date = date;
	}
	
	public Date(int day, int month, int year) {
		date = day + "-" + month + "-" + year;
	}
}
