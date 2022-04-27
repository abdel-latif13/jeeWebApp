package mybootapp.web;


import mybootapp.jpa.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    User user;

    @ModelAttribute("user")
    public User newUser() {
        var user = new User();
        logger.info("new user " + user);
        return user;
    }

    @RequestMapping(value = "/show")
    public String show(@ModelAttribute("user") User user) {
        logger.info("show user " + user);
        return "user";
    }

    @RequestMapping(value = "/login")
    public String login(//
                        @ModelAttribute("user") User user, //
                        RedirectAttributes attributes) {
        logger.info("login user " + user);
        user.setEmail("It's me");
        attributes.addFlashAttribute("message", "Bienvenue !");

        return "redirect:show";
    }

    @RequestMapping(value = "/logout")
    public String logout(//
                         @ModelAttribute("user") User user, //
                         RedirectAttributes attributes) {
        logger.info("logout user " + user);
        user.setEmail("Anonymous");
        attributes.addFlashAttribute("message", "Au revoir !");
        return "redirect:show";
    }

}
