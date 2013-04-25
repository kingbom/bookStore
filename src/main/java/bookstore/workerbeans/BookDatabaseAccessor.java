package bookstore.workerbeans;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import bookstore.entity.Book;
import bookstore.entity.Book.Category;
import bookstore.entity.User;
import bookstore.repository.BookRepository;
import bookstore.repository.UserRepository;

@Component
public class BookDatabaseAccessor{

	@Autowired
	private BookRepository repository;

	public List<Book> getAllBooks(){
		List<Book> books = repository.findAll();
		return books;
	}
	public List<Book> getSpecialBooks(){
		List<Book> books = repository.findBooksByIsSpecial(true);
		return books;
	}
	public List<Book> getBooks(Category category){
		List<Book> books =  repository.findBooksByCategory(category);
		return books;
	}
	
	public Book getBookByIsbn(String ISBN){
		Book book = repository.findBookByIsbn(ISBN);
		return book;
	}
	
	public List<Book> getBookByKeyword(String keyword){
		List<Book> books = repository.findBookByKeyword(keyword);
		return books;
	}
	
	public Book insertBook(Book bookToSave){
		Book bookInDb = repository.save(bookToSave);
		return bookInDb;
	}

}
