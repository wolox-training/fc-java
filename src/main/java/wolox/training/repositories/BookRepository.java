package wolox.training.repositories;

import org.springframework.data.repository.Repository;
import wolox.training.models.Book;

import java.util.List;

public interface BookRepository extends Repository<Book, Long> {
    Book findByAuthor(String author);

    Book findById(Long id);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(Long id);
}
