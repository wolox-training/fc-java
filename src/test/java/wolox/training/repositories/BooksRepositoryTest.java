package wolox.training.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import wolox.training.factories.BookFactory;
import wolox.training.models.Book;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BooksRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    private BookFactory bookFactory = new BookFactory();

    @Test
    public void findByAuthorTest() {
        Book book = bookFactory.build();
        entityManager.persistAndFlush(book);
        Book found = bookRepository.findByAuthor(book.getAuthor());
        assertThat(found).isEqualTo(book);
    }

    @Test
    public void findAllTest() {
        List<Book> books = bookFactory.buildList(3);
        for(Book book : books) {
            entityManager.persistAndFlush(book);
        }
        List<Book> booksFound = bookRepository.findAll();
        assertThat(booksFound.size()).isEqualTo(3);
    }

    @Test
    public void save() {
        Book book = bookFactory.build();
        Book saved = bookRepository.save(book);
        assertThat(book).isEqualTo(saved);
    }


    @Test
    public void findById() {
        Book book = bookFactory.build();
        entityManager.persistAndFlush(book);
        Book found = bookRepository.findById(book.getId()).get();
        assertThat(found).isEqualTo(book);
    }
}
