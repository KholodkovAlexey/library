package librarywebapp.auth;

import java.util.List;
import librarywebapp.db.AuthUserDAO;
import librarywebapp.db.DbHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class LibraryUserService implements UserDetailsService {
    
    private static final AuthUserDAO AUTH_DAO = new AuthUserDAO(DbHandler.JT); 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        List<AuthUser> users = AUTH_DAO.getAuthUser(username);
        
        if (users.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        } else {
            return users.get(0);
        }
        
    }
    
}
