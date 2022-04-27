package mybootapp.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.management.remote.JMXPrincipal;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "XGroup")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @OneToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY, mappedBy = "group"
    )
    @ToString.Exclude// Pour éviter les bloucles
    private List<Person> persons ;

    @Transient
    private long updateCounter = 0;

    /** Constructor **/
    public Group(String name) {
        this.name = name;
    }

    /*******************************************************************************************************************
     * methode to add a new person to the group
     * @param person the added person
     ******************************************************************************************************************/
    public void addPerson(Person person){
        if (persons == null){
            persons = new ArrayList<>();
        }
        persons.add(person);
        person.setGroup(this);
    }

    /*******************************************************************************************************************
     * Before updating a group
     ******************************************************************************************************************/
    @PreUpdate
    public void beforeUpdate() {
        System.err.println("PreUpdate of"+this);
    }

    /*******************************************************************************************************************
     * After updating a group
     ******************************************************************************************************************/
    @PostUpdate
    public void afterUpdate() {
        System.err.println("PostUpdate of"+this);
        updateCounter++;
    }





}
