package wolox.training.exceptions;

import wolox.training.models.Book;

public class BookAlreadyOwnedException extends Exception {
    public BookAlreadyOwnedException(Book book) {
        super("User already have book " + book.getTitle());
    }
}
