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
		if (bookHM.containsKey(book)) {
			updateQuantity(book,bookHM.get(book) + 1);			
		}
		else {
			bookHM.put(book, 1);
			setTotal(getTotal() + book.getPrice());
		}
	}
	
	public void removeBookFromCart(Book book) {
		int oldQuantity = bookHM.get(book);
		setTotal(getTotal() - (book.getPrice() * oldQuantity));
		bookHM.remove(book);
	}

	public void updateQuantity(Book book, int quantity) {
		int oldQuantity = bookHM.get(book);
		System.out.println("changing quantity from:" + oldQuantity + " to:" + quantity);
		
		setTotal(getTotal() - (book.getPrice() * oldQuantity));
		setTotal(getTotal() + (book.getPrice() * quantity));
		bookHM.put(book, quantity);
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
