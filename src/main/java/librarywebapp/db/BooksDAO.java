package librarywebapp.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import librarywebapp.json.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public final class BooksDAO {
    
    private final JdbcTemplate jt;
    
    public BooksDAO(JdbcTemplate jt) {
        this.jt = jt;
    }

    public List<Book> getBooks(int from_index, boolean order_by_author, boolean ascending_order) {
        final int returnCount = 5;
        String author_title = (order_by_author) ? "author" : "title", asc_desc = (ascending_order) ? "ASC" : "DESC";

        List<Book> raw_books = jt.query("SELECT isn, author, title, name FROM books LEFT OUTER JOIN users ON books.borrower_id = users.id ORDER BY " + author_title + " " + asc_desc,
                new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Book(rs.getString("isn"), rs.getString("author"),
                        rs.getString("title"), rs.getString("name"));
            }
        });

        int min = Math.min(from_index, raw_books.size()), max = Math.min(from_index + returnCount, raw_books.size());

        return raw_books.subList(min, max);
    }

    public boolean updateBook(Book book) {
        boolean success = !(book.getIsn().isEmpty() || book.getAuthor().isEmpty() || book.getTitle().isEmpty());

        if (success) {
            try {
                jt.update("UPDATE books SET author=?, title=?, borrower_id=(SELECT id FROM users WHERE name=?) WHERE isn=?",
                        new Object[]{book.getAuthor(), book.getTitle(), book.getBorrowerName(), book.getIsn()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    public boolean insertBook(Book book) {
        boolean success = !(book.getIsn().isEmpty() || book.getAuthor().isEmpty() || book.getTitle().isEmpty());

        if (success) {
            try {
                jt.update("INSERT INTO books (isn,author,title,borrower_id) VALUES (?,?,?,(SELECT id FROM users WHERE name=?))",
                        new Object[]{book.getIsn(), book.getAuthor(), book.getTitle(), book.getBorrowerName()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    public boolean deleteBook(Book book) {
        boolean success = true;

        if (success) {
            try {
                jt.update("DELETE FROM books WHERE isn=?", new Object[]{book.getIsn()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

}
