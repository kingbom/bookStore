package bookstore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import bookstore.CreditCard;
import bookstore.entity.Book;
import bookstore.entity.User;


public class Cart {
		
	private HashMap<Book,Integer> bookHM = new HashMap<Book, Integer>();
	private double total = 0.0;
	
	public Cart () {
	}
	
	public void addBookToCart(Book book) {
		bookHM.put(book, 1);
		setTotal(getTotal() + book.getPrice());
	}
	
	public void removeBookFromCart(Book book) {
		bookHM.remove(book);
		setTotal(getTotal() - book.getPrice());
	}

	public void updateQuantity(Book book, int quantity) {
		bookHM.put(book, quantity);
		setTotal(book.getPrice() * quantity);
	}
	
	public Iterator<Map.Entry<Book, Integer>> getIterator () {
		return bookHM.entrySet().iterator();
	}
	
	public  boolean checkoutOrder(User user, CreditCard card) {
		return true;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	

}
