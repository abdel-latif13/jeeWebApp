package mybootapp.web.security;

import mybootapp.jpa.model.Person;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;

public class MyUserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Person person;

    public MyUserPrincipal(Person user) {
        this.person = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorites = new LinkedList<GrantedAuthority>();

        person.getRoles().forEach((role) -> {
            authorites.add(new SimpleGrantedAuthority(role));
        });
        return authorites;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
