package wolox.training.repositories;

import org.springframework.data.repository.Repository;
import wolox.training.models.Book;
import wolox.training.models.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    User findByName(String name);

    Iterable findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);
}
