package wolox.training.repositories;

<<<<<<< HEAD
import org.springframework.boot.autoconfigure.security.SecurityProperties;
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> master
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import wolox.training.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    User findByName(String name);

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);

    List<User> findByBirthdateBetweenAndNameContainingAllIgnoreCase(LocalDate start, LocalDate stop, String name);
}
