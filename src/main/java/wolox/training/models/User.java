package wolox.training.models;

import com.google.common.base.Preconditions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wolox.training.exceptions.BookAlreadyOwnedException;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    private List<Book> books;

    @Column(nullable = false)
    private String password;

    private String role = "USER";

    public User() {}

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = Preconditions.checkNotNull(username, "Username cannot be empy");
    }

    public void setName(String name) {
        this.name = Preconditions.checkNotNull(name, "Name cannot be empy");
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = Preconditions.checkNotNull(birthdate, "Birthdate cannot be empy");
    }

    public void setBooks(List<Book> books) {
        this.books = Preconditions.checkNotNull(books, "Books cannot be empy");
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public boolean validPassword(String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addBook(Book book) throws BookAlreadyOwnedException {
        if(this.books.contains(book)) {
            throw new BookAlreadyOwnedException(book);
        } else {
            this.books.add(book);
        }
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }
}
