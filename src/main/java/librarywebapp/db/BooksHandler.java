package librarywebapp.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import librarywebapp.json.Book;
import librarywebapp.json.JSONArray;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

public class BooksHandler {

    static public JSONArray<Book> getBooks(int from_index, boolean order_by_author, boolean ascending_order) {
        final int returnCount = 5;
        String author_title = (order_by_author) ? "author" : "title", asc_desc = (ascending_order) ? "ASC" : "DESC";

        List<Book> raw_books = DbHandler.JT.query("SELECT isn, author, title, name FROM books LEFT OUTER JOIN users ON books.borrower_id = users.id ORDER BY " + author_title + " " + asc_desc,
                new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Book(rs.getString("isn"), rs.getString("author"),
                        rs.getString("title"), rs.getString("name"));
            }
        });

        int min = Math.min(from_index, raw_books.size()), max = Math.min(from_index + returnCount, raw_books.size());

        return new JSONArray<>(raw_books.subList(min, max));
    }

    static public boolean updateBook(Book book) {
        boolean success = !(book.getIsn().isEmpty() || book.getAuthor().isEmpty() || book.getTitle().isEmpty());

        if (success) {
            try {
                DbHandler.JT.update("UPDATE books SET author=?, title=?, borrower_id=(SELECT id FROM users WHERE name=?) WHERE isn=?",
                        new Object[]{book.getAuthor(), book.getTitle(), book.getBorrowerName(), book.getIsn()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    static public boolean insertBook(Book book) {
        boolean success = !(book.getIsn().isEmpty() || book.getAuthor().isEmpty() || book.getTitle().isEmpty());

        if (success) {
            try {
                DbHandler.JT.update("INSERT INTO books (isn,author,title,borrower_id) VALUES (?,?,?,(SELECT id FROM users WHERE name=?))",
                        new Object[]{book.getIsn(), book.getAuthor(), book.getTitle(), book.getBorrowerName()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    static public boolean deleteBook(Book book) {
        boolean success = true;

        if (success) {
            try {
                DbHandler.JT.update("DELETE FROM books WHERE isn=?", new Object[]{book.getIsn()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

private BooksHandler() {

    }
}
