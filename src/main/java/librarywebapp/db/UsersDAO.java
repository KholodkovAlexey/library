package librarywebapp.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import librarywebapp.json.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UsersDAO {

    private final JdbcTemplate jt;

    public UsersDAO(JdbcTemplate jt) {
        this.jt = jt;
    }

    public List<User> getUsers() {
        List<User> users = jt.query("SELECT name FROM users",
                new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(rs.getString("name"));
            }
        });

        return users;
    }

    public boolean updateUser(User user, String old_name) {
        boolean success = !(user.getName().isEmpty() || user.getPassword().isEmpty());

        if (success) {
            try {
                jt.update("UPDATE users SET name=?, password=? WHERE name=?",
                        new Object[]{user.getName(), user.getPassword(), old_name});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    public boolean insertUser(User user) {
        boolean success = !(user.getName().isEmpty() || user.getPassword().isEmpty());

        if (success) {
            try {
                jt.update("INSERT INTO users (name,password) VALUES (?,?)",
                        new Object[]{user.getName(), user.getPassword()});
            } catch (DataAccessException e) {
                success = false;
            }
        }

        return success;
    }

    public boolean deleteUser(User user) {
        boolean success = true;

        try {
            jt.update("DELETE FROM users WHERE name=?", new Object[]{user.getName()});
        } catch (DataAccessException e) {
            success = false;
        }

        return success;
    }

}
