package librarywebapp.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import librarywebapp.auth.AuthUser;
import librarywebapp.json.JSONArray;
import librarywebapp.json.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsersHandler {
    
    static public JSONArray<User> getUsers() {
        List<User> users = DbHandler.JT.query("SELECT name FROM users",
                new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(rs.getString("name"));
            }
        });
        
        return new JSONArray<>(users);
    }
    
    static public boolean updateUser(User user, String old_name) {
        boolean success = !(user.getName().isEmpty() || user.getPassword().isEmpty());

        if (success) {
            try {
                DbHandler.JT.update("UPDATE users SET name=?, password=? WHERE name=?",
                        new Object[]{user.getName(), user.getPassword(), old_name});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    static public boolean insertUser(User user) {
        boolean success = !(user.getName().isEmpty() || user.getPassword().isEmpty());

        if (success) {
            try {
                DbHandler.JT.update("INSERT INTO users (name,password) VALUES (?,?)",
                        new Object[]{user.getName(), user.getPassword() });
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    static public boolean deleteUser(User user) {
        boolean success = true;

        if (success) {
            try {
                DbHandler.JT.update("DELETE FROM users WHERE name=?", new Object[]{user.getName()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }
    
    static public List<AuthUser> getAuthUser(String name) {
        List<AuthUser> users = DbHandler.JT.query("SELECT name, password FROM users WHERE name=?",
                new Object[] {name},
                new RowMapper<AuthUser>() {
            @Override
            public AuthUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new AuthUser(rs.getString("name"), rs.getString("password"));
            }
        });
        
        return users;
    }
    
    private UsersHandler() {
        
    }
}
