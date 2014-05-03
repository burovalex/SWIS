package model;

import java.util.HashMap;
/**
 * Корзина
 * @author user
 *
 */
public class Cart {
	private User user;
	private HashMap <Product, Integer > mapProductQuantity;
	public Cart(User user, HashMap<Product, Integer> mapProductQuantity) {
		super();
		this.user = user;
		this.mapProductQuantity = mapProductQuantity;
	}
	
}
