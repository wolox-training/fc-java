package wolox.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;
import wolox.training.repositories.BookRepository;


@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateBook(@RequestBody User user, @PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userRepository.save(user);
    }

    @PostMapping("users/{id}/books")
    public void addBook(@RequestBody Book book, @PathVariable Long id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id));
        user.addBook(book);
    }

    @GetMapping("users/{userId}/books/{bookId}")
    public void removeBook(@PathVariable Long userId, @PathVariable Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(userId));
        user.addBook(book);
    }
}
