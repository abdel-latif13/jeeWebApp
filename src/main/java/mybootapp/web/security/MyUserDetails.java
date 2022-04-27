package mybootapp.web.security;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private JpaDao jpaDao;


    @Override
    public UserDetails loadUserByUsername(String username){
        Person user = jpaDao.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
