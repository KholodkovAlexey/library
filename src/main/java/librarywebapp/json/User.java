package librarywebapp.json;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

public final class User implements JSONConvertible{
    private final String name, password;
    
    public User(String name) {
        this.name = name;
        password = null;
    }
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public User(HttpServletRequest request) {
        this.name = request.getParameter("name");
        this.password = request.getParameter("name");
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    @Override
    public String toJSON() {
        String res = "{";
        
        res += "\"name\":\"" + name + '\"';
        
        return res + "}";
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean res = obj != null;
        
        if (res) {
            res = getClass() == obj.getClass();

            if (res) {
                User user = (User) obj;
                res = name.equals(user.getName());
            }
        }

        return res;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
}
