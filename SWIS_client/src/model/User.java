package model;
/**
 * Пользователь
 * @author user
 *
 */
public class User {
	private int id;
	private String fname;
	private String sname;
	private long cardNumber;
	private String passwordHash;
	public User(int id, String fname, String sname, long cardNumber) {
		super();
		this.id = id;
		this.fname = fname;
		this.sname = sname;
		this.cardNumber = cardNumber;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public String getSname() {
		return sname;
	}
	public long getCardNumber() {
		return cardNumber;
	}
}
