package librarywebapp.db;

import java.util.List;
import librarywebapp.json.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsersDAOTest {

    public UsersDAOTest() {
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
    public void testGetUsers() {
        DbHandler.PrepareDb();
        System.out.println("Testing getUsers");

        String name = "James";
        UsersDAO instance = new UsersDAO(DbHandler.JT);

        System.out.println("    Checking output size");
        List<User> result = instance.getUsers();
        assertTrue(result.size() == 4);

        System.out.println("    Checking output match");
        User expResult = new User(name);
        assertTrue(result.contains(expResult));

        System.out.println("    Test success");
    }

    @Test
    public void testUpdateUser() {
        DbHandler.PrepareDb();
        System.out.println("Testing updateUser");

        User user = new User("Jenny", "pass0");
        UsersDAO instance = new UsersDAO(DbHandler.JT);

        System.out.println("    Testing valid update case");
        String old_name = "Carl";
        assertTrue(instance.updateUser(user, old_name));

        /*System.out.println("    Testing repeated name case");
        old_name = "James";
        assertFalse(instance.updateUser(user, old_name));*/

        System.out.println("    Test success");
    }

    @Test
    public void testInsertUser() {
        DbHandler.PrepareDb();
        System.out.println("Testing insertUser");

        User user = new User("Jenny", "pass0");
        UsersDAO instance = new UsersDAO(DbHandler.JT);
        
        System.out.println("    Testing valid insert case");
        assertTrue(instance.insertUser(user));
        
        System.out.println("    Test success");
    }

    @Test
    public void testDeleteUser() {
        DbHandler.PrepareDb();
        System.out.println("Testing deleteUser");

        User user = new User("Carl");
        UsersDAO instance = new UsersDAO(DbHandler.JT);
        
        System.out.println("    Testing valid delete case");
        assertTrue(instance.deleteUser(user));
        
        System.out.println("    Test success");
    }

}
