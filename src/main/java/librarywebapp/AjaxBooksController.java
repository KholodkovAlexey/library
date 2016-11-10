package librarywebapp;

import javax.servlet.http.HttpServletRequest;
import librarywebapp.db.BooksHandler;
import librarywebapp.json.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxBooksController {
    
    @RequestMapping(value = "/ajax/books/select", method = RequestMethod.GET)
    String booksAjaxSelect(HttpServletRequest request) {
        int from_index = Integer.parseInt(request.getParameter("from_index"));
        boolean order_by_author = Boolean.parseBoolean(request.getParameter("order_by_author")),
                ascending_order = Boolean.parseBoolean(request.getParameter("ascending_order"));

        return BooksHandler.getBooks(from_index, order_by_author, ascending_order).toJSON();
    }

    @RequestMapping(value = "/ajax/books/update", method = RequestMethod.GET)
    String booksAjaxUpdate(HttpServletRequest request) {
        Book book = new Book(request);

        boolean success = BooksHandler.updateBook(book);

        String res = "{\"success\":" + Boolean.toString(success) + "}";

        return res;
    }

    @RequestMapping(value = "/ajax/books/insert", method = RequestMethod.GET)
    String booksAjaxInsert(HttpServletRequest request) {
        Book book = new Book(request);

        boolean success = BooksHandler.insertBook(book);

        String res = "{\"success\":" + Boolean.toString(success) + "}";

        return res;
    }

    @RequestMapping(value = "/ajax/books/delete", method = RequestMethod.GET)
    void booksAjaxDelete(HttpServletRequest request) {
        Book book = new Book(request);

        BooksHandler.deleteBook(book);
    }
}
