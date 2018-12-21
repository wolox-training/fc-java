package wolox.training.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import wolox.training.models.Book;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OpenLibraryService extends ApiService {
    private String book_url = System.getenv("ISBN_URL");

    public Book bookInfo(String isbn) {
        try {
            URL url = new URL(book_url + "/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            String response = obtainResponse(con);
            Book new_book = parseJSON(response, isbn);
            return new_book;
        } catch(Exception e) {
            return null;
        }
    }

    private Book parseJSON(String response, String isbn) {
        JSONObject obj = new JSONObject(response);
        if (obj.has("ISBN:" + isbn)) {
            JSONObject book_info = obj.getJSONObject("ISBN:0385472579");
            Book new_book = new Book();
            String authors_concatenated = "";
            String publishers_concatenated = "";
            new_book.setPages(book_info.getInt("number_of_pages"));
            new_book.setYear(book_info.getString("publish_date"));
            new_book.setTitle(book_info.getString("title"));
            new_book.setSubtitle(book_info.getString("subtitle"));
            new_book.setIsbn(isbn);
            JSONArray authors = book_info.getJSONArray("authors");
            for(Object author: authors){
                authors_concatenated = authors_concatenated + ((JSONObject)author).getString("name");
            }
            new_book.setAuthor(authors_concatenated);
            JSONArray publishers = book_info.getJSONArray("publishers");
            for(Object publisher: publishers){
                publishers_concatenated = publishers_concatenated + ((JSONObject)publisher).getString("name");
            }
            new_book.setPublisher(publishers_concatenated);
            return new_book;
        } else {
            return null;
        }
    }
}
