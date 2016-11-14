package librarywebapp;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import librarywebapp.db.BooksDAO;
import librarywebapp.db.DbHandler;
import librarywebapp.json.Book;
import librarywebapp.json.JSONArray;
import librarywebapp.json.RequestSuccess;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class AjaxBooksController {
    
    private static final BooksDAO BOOKS_DAO = new BooksDAO(DbHandler.JT);
    
    @RequestMapping(value = "/ajax/books/select", method = RequestMethod.GET)
    String booksAjaxSelect(HttpServletRequest request) {
        int from_index = Integer.parseInt(request.getParameter("from_index"));
        boolean order_by_author = Boolean.parseBoolean(request.getParameter("order_by_author")),
                ascending_order = Boolean.parseBoolean(request.getParameter("ascending_order"));
        
        List<Book> books = BOOKS_DAO.getBooks(from_index, order_by_author, ascending_order);
        
        JSONArray<Book> jbooks = new JSONArray<>(books);

        return jbooks.toJSON();
    }

    @RequestMapping(value = "/ajax/books/update", method = RequestMethod.GET)
    String booksAjaxUpdate(HttpServletRequest request) {
        Book book = new Book(request);

        boolean success = BOOKS_DAO.updateBook(book);
        
        RequestSuccess jsuccess = new RequestSuccess(success);

        return jsuccess.toJSON();
    }

    @RequestMapping(value = "/ajax/books/insert", method = RequestMethod.GET)
    String booksAjaxInsert(HttpServletRequest request) {
        Book book = new Book(request);

        boolean success = BOOKS_DAO.insertBook(book);

        RequestSuccess jsuccess = new RequestSuccess(success);

        return jsuccess.toJSON();
    }

    @RequestMapping(value = "/ajax/books/delete", method = RequestMethod.GET)
    void booksAjaxDelete(HttpServletRequest request) {
        Book book = new Book(request);

        BOOKS_DAO.deleteBook(book);
    }
}
