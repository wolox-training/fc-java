package wolox.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wolox.training.exceptions.UserNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody User user, @PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userRepository.save(user);
    }

}
