package model;
/**
 * Программный продукт - товар.
 * @author user
 *
 */
public class Product {
	private int id;	//идентификатор - первичный ключ БД
	private String name;
	private String decsription;
	private /*Category*/ String category;
	private double price;
	private /*Developer*/ String developer;
	public Product(int ID, String name, String decsription, String category,
			double price, String developer) {
		super();
		this.id = ID;
		this.name = name;
		this.decsription = decsription;
		this.category = category;
		this.price = price;
		this.developer = developer;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDecsription() {
		return decsription;
	}
	public String getCategory() {
		return category;
	}
	public double getPrice() {
		return price;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDescription(String descr){
		decsription = descr;
	}
	
}
