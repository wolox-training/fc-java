package wolox.training.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import wolox.training.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    User findByName(String name);

    Iterable findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);

    @Query("SELECT u FROM User u WHERE lower(u.name) LIKE %:search% AND u.birthdate BETWEEN :from AND :to")
    List<User> findBetweenDates(@Param("from")LocalDate from, @Param("to") LocalDate to, @Param("search") String search);
}
