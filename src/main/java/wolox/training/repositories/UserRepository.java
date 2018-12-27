package wolox.training.repositories;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.repository.Repository;
import wolox.training.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    User findByName(String name);

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);
}
