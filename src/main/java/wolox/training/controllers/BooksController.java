package wolox.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;
import wolox.training.services.OpenLibraryService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OpenLibraryService openLibraryService;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(HttpServletResponse response, @RequestBody Book book) {
        response.setContentType("application/json");
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
        return bookRepository.save(book);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getOrCreateByIsbn(@PathVariable String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            book = openLibraryService.bookInfo(isbn);
            if (book == null) {
                return new ResponseEntity(System.getenv("Book not found in ISBN service"), HttpStatus.BAD_REQUEST);
            }
            bookRepository.save(book);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/query")
    public List<Book> findByYearGenrePublisher(@RequestParam(name="year", required=true) String year,
                                               @RequestParam(name="genre", required=true) String name,
                                               @RequestParam(name="publisher", required=true) String publisher) {
        return bookRepository.findByYearAndGenreAndPublisher(year, name, publisher);
    }
}
