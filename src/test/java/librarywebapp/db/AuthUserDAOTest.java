package librarywebapp.db;

import java.util.List;
import librarywebapp.auth.AuthUser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthUserDAOTest {
    
    public AuthUserDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DbHandler.PrepareDb();
    }
    
    @After
    public void tearDown() {
    }

   @Test
    public void testGetAuthUser() {
        System.out.println("Testing getAuthUser");
        
        String name = "James";
        AuthUserDAO instance = new AuthUserDAO(DbHandler.JT);
        
        System.out.println("    Checking output size");
        List<AuthUser> result = instance.getAuthUser(name);
        assertTrue(result.size() == 1);
        
        System.out.println("    Checking objects match");
        AuthUser expResult = new AuthUser("James", "pass2");
        assertEquals(expResult, result.get(0));
        
        System.out.println("    Test success");
    }
    
}
