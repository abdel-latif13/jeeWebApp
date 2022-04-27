package mybootapp.web;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Group;
import mybootapp.jpa.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.*;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    JpaDao jpaDao;
    @Autowired
    GroupValidator validator;
    protected final Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {
        jpaDao.addGroup(new Group("g1"));
        jpaDao.addGroup(new Group("g2"));
    }

    @RequestMapping("/list" )
    public ModelAndView listGroups() {
        return new ModelAndView("groupList", "groups", jpaDao.findAllGroups());
    }

    @ModelAttribute
    public Group newGroup(@RequestParam(value="id",required = false)Long groupId){
        if(groupId != null){
            logger.info("find group"+groupId);
            var g = jpaDao.findGroup(groupId);
            return g;
        }
        Group g = new Group();
        g.setName("");
        return g;
    }

    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public String editGroup(@ModelAttribute Group p){
        return "groupForm";
    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public String saveGroup(@ModelAttribute Group g, BindingResult result){
        validator.validate(g,result);
        if(result.hasErrors()){
            return "groupForm";
        }
        jpaDao.addGroup(g);
        return "redirect:list";
    }

    @RequestMapping("/find")
    public ModelAndView findGroup(String name){
        final var result = jpaDao.findGroupWithNameLike(name);
        return new ModelAndView("groupList","groups",result);
    }

    @ModelAttribute("PersonsInTheGroup")
    public Map<Long, String> allPersonsInTheGroup(@RequestParam(value="id",required = false)Long groupId){
        List <Person> personList;
        Map<Long, String> persons = new LinkedHashMap<>();
        if(groupId != null){
            logger.info("find group"+groupId);
            var g = jpaDao.findGroup(groupId);
            personList = jpaDao.findPersonsInGroup(g);
            if(personList.size()!=0) {
                for (int i = 0; i < personList.size(); i++) {
                    Long id = personList.get(i).getId();
                    String firstName = personList.get(i).getFirstName();
                    String lastName = personList.get(i).getLastName();
                    persons.put(id, firstName+" "+lastName);
                }
            }
        }
        return persons;
    }


    @ModelAttribute("PersonsWithoutGroup")
    public Map<Long, String> allPersonsWithoutGroup(){
        List<Person> personList = jpaDao.findAllPersonsWithoutGroup();
        Map<Long, String> persons = new LinkedHashMap<>();
        for(int i=0; i<personList.size(); i++){
            Long id = personList.get(i).getId();
            String firstName = personList.get(i).getFirstName();
            persons.put(id,firstName);
        }
        return persons;
    }

    @RequestMapping("/addPersonToGroup")
    public ModelAndView addPersonToGroup(@RequestParam(value="id",required = false)Long groupId, @RequestParam(value="pid",required=false) Long personId){
        Group g = jpaDao.findGroup(groupId);
        if(g.getPersons() == null){
            g.setPersons(new ArrayList<>());
        }
        jpaDao.updateGroup(g);
        Person pToAdd = jpaDao.findPerson(personId);
        g.getPersons().add(pToAdd);
        pToAdd.setGroup(g);
        jpaDao.updatePerson(pToAdd);
        jpaDao.updateGroup(g);
        return new ModelAndView("groupForm");
    }

    @RequestMapping("/removePersonFromGroup")
    public ModelAndView removePersonFromGroup(@RequestParam(value="id",required = false)Long groupId, @RequestParam(value="pid",required=false) Long personId){
        Group g = jpaDao.findGroup(groupId);
        if(g.getPersons() == null){
            return new ModelAndView("groupForm");
        }
        Person pToAdd = jpaDao.findPerson(personId);
        g.getPersons().remove(pToAdd);
        pToAdd.setGroup(null);
        jpaDao.updatePerson(pToAdd);
        jpaDao.updateGroup(g);
        return new ModelAndView("groupForm");
    }



}
