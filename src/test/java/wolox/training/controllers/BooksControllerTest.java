package wolox.training.controllers;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import wolox.training.factories.BookFactory;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;
import wolox.training.services.OpenLibraryService;
import wolox.training.utils.JsonHelper;

import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private OpenLibraryService openLibraryService;

    private BookFactory bookFactory = new BookFactory();
    private Faker faker = new Faker();

    @Test
    public void findAll() throws Exception {
        List<Book> books = bookFactory.buildList(3);

        given(bookRepository.findAll()).willReturn(books);

        mvc.perform(get("/api/books")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].title", is(books.get(0).getTitle())));
    }

    @Test
    public void create() throws Exception {
        Book book = bookFactory.build();

        mvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonHelper.asJson(book)))
            .andExpect(status().isCreated())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    public void createFailure() throws Exception {
        Book book = new Book();
        book.setTitle(faker.book().title());

        mvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.asJson(book)))
                .andExpect(status().is(400));
    }
}
