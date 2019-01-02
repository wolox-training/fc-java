package wolox.training.factories;

import com.github.javafaker.Faker;
import wolox.training.models.Book;

public class BookFactory extends FactoryBase<Book> {

    Faker faker = new Faker();

    @Override
    public Book build() {
        Book book = new Book();
        book.setTitle(faker.book().title());
        book.setGenre(faker.book().genre());
        book.setAuthor(faker.book().author());
        book.setIsbn(faker.lorem().word());
        book.setPages(faker.number().numberBetween(1,1000));
        book.setSubtitle(faker.book().title());
        book.setPublisher(faker.book().publisher());
        book.setYear(String.valueOf(faker.number().numberBetween(1500, 2019)));

        return book;
    }
}
