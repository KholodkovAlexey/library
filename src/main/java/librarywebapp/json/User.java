package librarywebapp.json;

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
    
}
