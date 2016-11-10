package librarywebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraryController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String defaultControl(ModelMap model) {
        //books page will be page by default
        return booksControl(model);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    String booksControl(ModelMap model) {
        return "books_page";
    }

    @RequestMapping(value = "/users")
    String usersControl(ModelMap model) {
        return "users_page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String signinControl() {
        return "login";
    }

}
