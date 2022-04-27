package mybootapp.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity(name = "Person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false)
    @Column(name = "first_name", length = 200, nullable = false, unique = false)
    private String firstName;

    @Basic(optional = false)
    @Column(name = "last_name", length = 200, nullable = false, unique = false)
    private String lastName;

    @Column(name = "email", length = 200, nullable = false, unique = true)
    private String email;

    private String webSite;

    @Basic()
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthday")
    private Date birthday;

    @Basic
    @Column(name = "password", length = 200, nullable = false)
    private String password;

    //TODO: verifier si on met id de groupe ou tout le groupe ( verifier comme l'afficher ausi)
    @ManyToOne(optional = true)
    @JoinColumn(name = "xgroup")
    @ToString.Exclude // pour éviter les boucles
    private Group group;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> roles;

    @Column(name = "reset_password_token")// token
    private String resetPasswordToken;


    @Transient
    private long updateCounter = 0;



    public Person(String firstName, String lastName, String email, String webSite, Date birthday, String password, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.webSite = webSite;
        this.birthday = birthday;
        this.password = password;
        this.group = null;
        this.roles= Set.of("USER");
    }
    public Person(String firstName, String lastName, String email, String webSite, Date birthday, String password, Group group, Set roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.webSite = webSite;
        this.birthday = birthday;
        this.password = password;
        this.group = null;
        this.roles= roles;
    }
    public Person(String email,String password,Set roles, Group g){
        this.firstName= email; // formellement
        this.lastName=email; // formellement
        this.email=email;
        this.webSite = email;
        this.birthday = null;
        this.password = password;
        this.group = g;
        this.roles=roles;
    }
    public Person(String email,String password,Set roles, Group g,String resetPasswordToken){
        this.firstName= email; // formellement
        this.lastName=email; // formellement
        this.email=email;
        this.webSite = email;
        this.birthday = null;
        this.password = password;
        this.group = g;
        this.roles=roles;
        this.resetPasswordToken = resetPasswordToken;
    }

    @PreUpdate
    public void beforeUpadte() {
        System.err.println("PreUpdate of"+this);
    }

    @PostUpdate
    public void afterUpadte() {
        System.err.println("PostUpdate of"+this);
        updateCounter++;
    }





}
