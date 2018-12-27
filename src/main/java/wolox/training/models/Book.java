package wolox.training.models;

import com.google.common.base.Preconditions;

import javax.persistence.*;

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
        this.title = Preconditions.checkNotNull(title, "Title cannot be empy");
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = Preconditions.checkNotNull(subtitle, "Subtitle cannot be empy");
    }

    public void setAuthor(String author) {
        this.author = Preconditions.checkNotNull(author, "Author cannot be empy");
    }

    public void setPublisher(String publisher) {
        this.publisher = Preconditions.checkNotNull(publisher, "Publisher cannot be empy");
    }

    public void setYear(String year) {
        this.year = Preconditions.checkNotNull(year, "Year cannot be empy");
    }

    public void setPages(Integer pages) {
        this.pages = Preconditions.checkNotNull(pages, "Pages cannot be empy");
    }

    public void setIsbn(String isbn) {
        this.isbn = Preconditions.checkNotNull(isbn, "isbn cannot be empy");
    }
}
