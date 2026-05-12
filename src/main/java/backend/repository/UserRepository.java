package backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shared.User;

/*
JPA allows for access to CRUD methods without having to implement them
at runtime: save(), findById(), findAll(), delete(), ... etc
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
