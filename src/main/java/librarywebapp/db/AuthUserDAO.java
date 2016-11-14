package librarywebapp.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import librarywebapp.auth.AuthUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AuthUserDAO {
    
    private final JdbcTemplate jt;
    
    public AuthUserDAO(JdbcTemplate jt) {
        this.jt = jt;
    }
    
    public List<AuthUser> getAuthUser(String name) {
        List<AuthUser> users = jt.query("SELECT name, password FROM users WHERE name=?",
                new Object[] {name},
                new RowMapper<AuthUser>() {
            @Override
            public AuthUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new AuthUser(rs.getString("name"), rs.getString("password"));
            }
        });
        
        return users;
    }
    
}
