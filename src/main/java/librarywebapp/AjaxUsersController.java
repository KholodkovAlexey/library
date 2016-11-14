package librarywebapp;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import librarywebapp.db.DbHandler;
import librarywebapp.db.UsersDAO;
import librarywebapp.json.JSONArray;
import librarywebapp.json.RequestSuccess;
import librarywebapp.json.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class AjaxUsersController {
    
    private static final UsersDAO USERS_DAO = new UsersDAO(DbHandler.JT);
    
    @RequestMapping(value = "/ajax/users/select", method = RequestMethod.GET)
    String usersAjaxSelect(HttpServletRequest request) {
        List<User> users = USERS_DAO.getUsers();

        JSONArray<User> jusers = new JSONArray<>(users);
        
        return jusers.toJSON();
    }

    @RequestMapping(value = "/ajax/users/update", method = RequestMethod.GET)
    String usersAjaxUpdate(HttpServletRequest request) {
        User user = new User(request);
        String old_name = request.getParameter("oldname");
        System.out.println("Updating " + old_name + " to " + user.getName());

        boolean success = USERS_DAO.updateUser(user, old_name);

        RequestSuccess jsuccess = new RequestSuccess(success);

        return jsuccess.toJSON();
    }

    @RequestMapping(value = "/ajax/users/insert", method = RequestMethod.GET)
    String usersAjaxInsert(HttpServletRequest request) {
        User user = new User(request);

        boolean success = USERS_DAO.insertUser(user);

        RequestSuccess jsuccess = new RequestSuccess(success);

        return jsuccess.toJSON();
    }

    @RequestMapping(value = "/ajax/users/delete", method = RequestMethod.GET)
    String usersAjaxDelete(HttpServletRequest request) {
        User user = new User(request);

        boolean success = USERS_DAO.deleteUser(user);
        
        RequestSuccess jsuccess = new RequestSuccess(success);

        return jsuccess.toJSON();
    }
}
