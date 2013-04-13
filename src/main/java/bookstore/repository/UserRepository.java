package bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import bookstore.entity.User;
import bookstore.entity.*;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByEmailIgnoreCaseAndPassword(String email, String password);
}
