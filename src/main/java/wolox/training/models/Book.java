package wolox.training.models;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

>>>>>>> master
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String genre;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Book() {}

    public long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getYear() {
        return year;
    }

    public Integer getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
