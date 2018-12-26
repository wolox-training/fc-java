package wolox.training.models;

import com.google.common.base.Preconditions;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
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

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }
}
