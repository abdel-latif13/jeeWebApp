package mybootapp.web;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServices {
    @Autowired
    private JpaDao jpaDao;

    public void updateResetPasswordToken(String token, String email) throws Exception {
        Person person = jpaDao.findByEmail(email);
        if (person != null){
            person.setResetPasswordToken(token);
            jpaDao.updatePerson(person);
        }
        else {
            throw new Exception("CouldNotFoundException"+email);
        }
    }
    public Person getByResetPasswordToken(String token){
        return jpaDao.findByResetPasswordToken(token);
    }
    public void updatePassword(Person person, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        person.setPassword(encodedPassword);

        person.setResetPasswordToken(null);
        jpaDao.updatePerson(person);
    }

}
