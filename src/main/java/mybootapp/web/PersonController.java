package mybootapp.web;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Group;
import mybootapp.jpa.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/person")
public class PersonController {
    /*
     * injection de la dao de manipulation de person
     */

    @Autowired
    JpaDao jpaDao;

    @Autowired
    PersonValidator validator;

    protected final Log logger = LogFactory.getLog(getClass());

    /* we add two persons every time we start the app*/
    @PostConstruct
    public void init() {
        int i = 0;
        Person p1 = new Person("youssef", "HAOUES", "houasyoussef@gmail.com", null, new Date(2000, 5, 8), "pass1", null);
        Person p2 = new Person("abdel", "BENS", "abdel.youssef.JEE@gmail.com", null, new Date(1999, 12, 5), "pass2", null);
        jpaDao.addPerson(p1);
        jpaDao.addPerson(p2);

        i = 0;
        for (int x=0; x<=10; x++){
            i ++;
            String emailPerson = "User_" + String.valueOf(i);
            jpaDao.addPerson(new Person(emailPerson,emailPerson,"User_"+i+"@gmail.com",null,new Date(2000,5,8), "User_"+i,null));
        }


    }


    @RequestMapping("/list" )
    public ModelAndView listPersons() {
       return new ModelAndView("personList", "persons", jpaDao.findAllPersons());
    }



    @ModelAttribute
    public Person newPerson(@RequestParam(value="id",required = false) Long personId){
        if(personId != null){
            logger.info("find Person" + personId);
            //var p = personRepository.findById(personId);
            var p = jpaDao.findPerson(personId);
            //return p.get();
            return p;
        }
        Person p = new Person();
        p.setFirstName("");
        p.setLastName("");
        p.setEmail("");
        p.setWebSite("");
        p.setBirthday(new Date(1999, Calendar.NOVEMBER,12));
        p.setPassword("");
        p.setGroup(null);
        p.setRoles(Set.of("USER"));
        logger.info("new person = " + p);
        return p;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPerson(@ModelAttribute Person p) {
        return "personForm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute @Valid Person p, BindingResult result) {
        validator.validate(p, result);
        if (result.hasErrors()) {
            return "personForm";
        }
        jpaDao.addPerson(p);
        return "redirect:list";
    }

    @RequestMapping("/find")
    public ModelAndView findPersons(String name){
        final var result = jpaDao.findPersonWithNameLike(name);
        return new ModelAndView("personList","persons",result);
    }

    @RequestMapping(value ="/editMe", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView editMe( Principal principal){
        String email = principal.getName();
        Person person = jpaDao.findByEmail(email);
        return new ModelAndView("personForm","person",person);
    }


    @RequestMapping("/myGroup")
    @ResponseBody
    public ModelAndView myGroup( Principal principal){
        String email = principal.getName();
        Person person = jpaDao.findByEmail(email);
        final var result = jpaDao.findPersonsInGroup(person.getGroup());
        return new ModelAndView("personList", "persons", result);
    }

    @ModelAttribute("persons")
    Collection<Person> persons() {
        logger.info("Making list of persons");
        return jpaDao.findAllPersons();
    }


}

