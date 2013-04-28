package bookstore.workerbeans;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bookstore.entity.Book;
import bookstore.entity.Book.Category;
import bookstore.entity.User;
import bookstore.repository.BookRepository;
import bookstore.repository.UserRepository;

@Component
public class BookDatabaseAccessor{

	@Autowired
	private BookRepository repository;

	@Transactional
	public List<Book> getAllBooks(){
		List<Book> books = repository.findAll();
		return books;
	}
	@Transactional
	public List<Book> getSpecialBooks(){
		List<Book> books = repository.findBooksByIsSpecial(true);
		return books;
	}
	@Transactional
	public List<Book> getBooks(Category category){
		List<Book> books =  repository.findBooksByCategory(category);
		return books;
	}
	@Transactional
	public Book getBookByIsbn(String ISBN){
		Book book = repository.findBookByIsbn(ISBN);
		return book;
	}
	@Transactional
	public List<Book> getBookByKeyword(String keyword){
		List<Book> books = repository.findBookByKeyword(keyword);
		return books;
	}
	@Transactional
	public Book insertBook(Book bookToSave){
		Book bookInDb = repository.save(bookToSave);
		return bookInDb;
	}

}
