package wolox.training.repositories;

import org.h2.store.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import wolox.training.models.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends Repository<Book, Long> {
    Book findByAuthor(String author);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    Book findByIsbn(String isbn);

    List<Book> findByYearAndGenreAndPublisher(String year, String genre, String publisher);
}
