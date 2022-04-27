package mybootapp.web.security;

import mybootapp.jpa.dao.JpaDao;
import mybootapp.jpa.model.Group;
import mybootapp.jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Set;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService myUserDetailsService;

    @Autowired
    JpaDao jpaDao;

    @Bean
    public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @PostConstruct
    public void init() {
        var encoder = passwordEncoder();
        Group g1 = new Group("g14");
        jpaDao.addGroup(g1);
        var aa = new Person("aaa","aaa","admin@gmail.com","aaa",new Date(5,5,2000), encoder.encode("admin"),null, Set.of("ADMIN", "USER"));
        var bb = new Person("user@gmail.com", encoder.encode("user"), Set.of("USER"),g1);
        g1.addPerson(bb);
        jpaDao.addPerson(aa);
        jpaDao.addPerson(bb);
        System.out.println("--- INIT SPRING SECURITY");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // -- ne pas activer la protection CSRF
                .csrf().disable()
                // -- URL sans authentification
                .authorizeRequests()//
                .antMatchers("/", "/webjars/**", //
                        "/home", "/login","/forgot_password","/reset_password")//
                .permitAll()//
                // -- limitez l'accès à user
                .antMatchers("/user/**")
                .hasAnyAuthority("ADMIN")
                // -- Les autres URL nécessitent une authentification
                .anyRequest().authenticated()
                // -- Nous autorisons un formulaire de login
                .and().formLogin()
                .loginPage("/login").successForwardUrl("/")
                .permitAll()
                // -- Nous autorisons un formulaire de logout
                .and().logout().permitAll();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
