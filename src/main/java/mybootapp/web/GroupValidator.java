package mybootapp.web;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Group;
import mybootapp.jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Service
public class GroupValidator implements Validator {


    @Autowired
    JpaDao jpa;

    @Override
    public boolean supports(Class<?> clazz) {
        return Group.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Group group = (Group) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","group.name");

        if(existName(group)){
            errors.rejectValue("name", "group.name.exit");
        }
    }

    public boolean existName(Group group){
        String name = group.getName();
        List<Group> list = jpa.findAllGroups();
        for(int i=0;i< list.size();i++){
            if(list.get(i).getId() != group.getId()){
                if(list.get(i).getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
}
