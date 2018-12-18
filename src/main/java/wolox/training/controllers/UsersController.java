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
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
        return userRepository.save(user);
    }

//    @PostMapping("/{userId}/books/{bookId}")
//    public User addBook(@PathVariable Long userId, @PathVariable Long bookId) {
//        return userRepository.findById(userId);
//    }

    @GetMapping("/{userId}/books/{bookId}")
    public User getBook(@PathVariable Long userId, @PathVariable Long bookId) {
        User user = userRepository.findById(userId)
                        .map((user1)->
                                user1 )
                        .orElseThrow(()-> new UserNotFoundException(userId));
        return user;
    }
}
