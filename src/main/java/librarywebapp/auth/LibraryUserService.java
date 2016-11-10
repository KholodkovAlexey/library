package librarywebapp.auth;

import java.util.List;
import librarywebapp.db.UsersHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LibraryUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        List<AuthUser> users = UsersHandler.getAuthUser(username);
        
        if (users.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        } else {
            return users.get(0);
        }
        
    }
    
}
