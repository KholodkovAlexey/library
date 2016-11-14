package librarywebapp.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser implements UserDetails {

    private final String ROLE = "ROLE_USER";
    private final Collection<GrantedAuthority> authorities;
    private String username;
    private String password;

    public AuthUser(String username, String password) {
        super();
        this.username = username;
        this.password = password;

        GrantedAuthority grandAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return ROLE.trim();
            }
        };

        authorities = new ArrayList<>(1);
        authorities.add(grandAuthority);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = obj != null;
        
        if (res) {
            res = getClass() == obj.getClass();

            if (res) {
                AuthUser user = (AuthUser) obj;
                res = username.equals(user.getUsername()) && password.equals(user.getPassword());
            }
        }

        return res;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        return hash;
    }
}
