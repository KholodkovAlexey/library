package librarywebapp.db;

import java.util.List;
import librarywebapp.json.Book;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BooksDAOTest {
    
    public BooksDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetBooks() {
        DbHandler.PrepareDb();
        System.out.println("Testing getBooks");
        
        int from_index = 0;
        boolean order_by_author = true;
        boolean ascending_order = true;
        BooksDAO instance = new BooksDAO(DbHandler.JT);
        
        System.out.println("    Checking output size");
        List<Book> result = instance.getBooks(from_index, order_by_author, ascending_order);
        assertFalse(result.isEmpty());
        
        System.out.println("    Checking output match");
        Book expResult = new Book("0000-0000-0001");
        assertTrue(result.contains(expResult));
        
        System.out.println("    Test success");
    }

    @Test
    public void testUpdateBook() {
        DbHandler.PrepareDb();
        System.out.println("Testing updateBook");
        
        Book book = new Book("1111-1111-1111", "UpdateTestAuthor", "UpdateTestTitle", null);
        BooksDAO instance = new BooksDAO(DbHandler.JT);
        assertTrue(instance.updateBook(book));
        
        System.out.println("    Test success");
    }

    @Test
    public void testInsertBook() {
        DbHandler.PrepareDb();
        System.out.println("Testing insertBook");
        
        Book book = new Book("1111-1111-1111", "InsertTestAuthor", "InsertTestTitle", null);
        BooksDAO instance = new BooksDAO(DbHandler.JT);
        assertTrue(instance.insertBook(book));
        
        System.out.println("    Test success");
    }

    @Test
    public void testDeleteBook() {
        DbHandler.PrepareDb();
        System.out.println("Testing deleteBook");
        
        Book book = new Book("0001-0001-0001", "DeleteTestAuthor", "DeleteTestTitle", null);
        BooksDAO instance = new BooksDAO(DbHandler.JT);
        assertTrue(instance.deleteBook(book));
        
        System.out.println("    Test success");
    }
    
}
