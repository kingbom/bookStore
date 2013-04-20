package bookstore.workerbeans;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
		return null;
	}
	public List<Book> getSpecialBooks(){
		return null;
	}
	public List<Book> getBooks(Category category){
		return null;	
	}
	
	public Book getBookByIsbn(String ISBN){
		return null;
	}
	
	public List<Book> getBookByKeyword(String keyword){
		return null;
	}

}
