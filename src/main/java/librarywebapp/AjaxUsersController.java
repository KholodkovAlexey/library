package librarywebapp;

import javax.servlet.http.HttpServletRequest;
import librarywebapp.db.UsersHandler;
import librarywebapp.json.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxUsersController {
    
    @RequestMapping(value = "/ajax/users/select", method = RequestMethod.GET)
    String usersAjaxSelect(HttpServletRequest request) {
        String res =  UsersHandler.getUsers().toJSON();
        return res;
    }

    @RequestMapping(value = "/ajax/users/update", method = RequestMethod.GET)
    String usersAjaxUpdate(HttpServletRequest request) {
        User user = new User(request);
        String old_name = request.getParameter("oldname");
        System.out.println("Updating " + old_name + " to " + user.getName());

        boolean success = UsersHandler.updateUser(user, old_name);

        String res = "{\"success\":" + Boolean.toString(success) + "}";

        return res;
    }

    @RequestMapping(value = "/ajax/users/insert", method = RequestMethod.GET)
    String usersAjaxInsert(HttpServletRequest request) {
        User user = new User(request);

        boolean success = UsersHandler.insertUser(user);

        String res = "{\"success\":" + Boolean.toString(success) + "}";

        return res;
    }

    @RequestMapping(value = "/ajax/users/delete", method = RequestMethod.GET)
    String usersAjaxDelete(HttpServletRequest request) {
        User user = new User(request);

        boolean success = UsersHandler.deleteUser(user);
        
        String res = "{\"success\":" + Boolean.toString(success) + "}";

        return res;
    }
}
