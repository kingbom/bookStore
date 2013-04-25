package bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bookstore.entity.*;
import bookstore.entity.Book.Category;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	List<Book> findBooksByIsSpecial(Boolean isSpecial);
	List<Book> findBooksByCategory(Category category);
	Book findBookByIsbn(String ISBN);
	List<Book> findBookByAuthor(String author);
	List<Book> findBookByTitle(String title);
	@Query("SELECT b FROM Book AS b WHERE b.title LIKE CONCAT('%',:keyword, '%')")
	List<Book> findBookByKeyword(@Param("keyword")String keyword);
}
