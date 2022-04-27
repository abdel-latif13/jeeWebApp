package mybootapp.web;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PersonValidator implements Validator {

    @Autowired
    JpaDao jpa;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        Person person = (Person)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","person.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName", "person.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","person.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"birthday","person.birthday");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","person.password");

        if(!(person.getEmail().contains("@"))){
            errors.rejectValue("email", "person.email.notValid");
        }
        if(!(person.getWebSite().contains("www"))&&!(person.getWebSite().contains("."))){
            errors.rejectValue("webSite", "person.webSite.notValid");
        }

        if(existMail(person)){
            errors.rejectValue("email", "person.email.exist");
        }
        if(person.getBirthday() != null) {
            if (person.getBirthday().after(new Date())) {
                errors.rejectValue("birthday", "person.birthday.big");
            }
        }
    }

    public boolean existMail(Person person){
        String email = person.getEmail();
        List<Person> list = jpa.findAllPersons();
        for(int i=0;i< list.size();i++){
            if(list.get(i).getId() != person.getId()){
                if(list.get(i).getEmail().equals(email)){
                    return true;
                }
            }
        }
        return false;
    }

}
