package mybootapp.jpa.dao;

import mybootapp.jpa.model.Group;
import mybootapp.jpa.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaDao implements IDirectoryDao{


    @PersistenceContext
    EntityManager em;

    /*******************************************************************************************************************
     * Find every person in one group
     * @param id the group id
     * @return list of persons
     *****************************************************************************************************************/
    @Override
    public List<Person> findPersonsInGroup(Group id) {
        String query= "SELECT p from Person p where p.group= "+ id.getId();
        TypedQuery<Person> q = em.createQuery(query, Person.class);
        return q.getResultList();
    }

    /*******************************************************************************************************************
     * find all the groups
     * @return Collection of all the groups
     ******************************************************************************************************************/
    @Override
    public List<Group> findAllGroups() {
        String query = "SELECT p FROM Group p";
        TypedQuery<Group> q = em.createQuery(query,Group.class);
        return q.getResultList();
    }

    /*******************************************************************************************************************
     * Find all the persons
     * @return Collection of persons
     ******************************************************************************************************************/
    @Override
    public List<Person> findAllPersons() {
        String query = "SELECT p FROM Person p";
        TypedQuery<Person> q = em.createQuery(query,Person.class);
        return q.getResultList();
    }

    /*******************************************************************************************************************
     * Return all the persons without group
     ******************************************************************************************************************/
    //TODO: verifier son fonctionnement
    @Override
    public List<Person> findAllPersonsWithoutGroup() {
        String query = "SELECT p FROM Person p where p.group = " +null;
        TypedQuery<Person> q = em.createQuery(query,Person.class);
        return q.getResultList();
    }

    /*******************************************************************************************************************
     * return the persons with name or last name contains the param
     * @param name the searched word
     * @return list of persons that contains the word
     ******************************************************************************************************************/
    @Override
    public List<Person> findPersonWithNameLike(String name) {
        String query = "SELECT p FROM Person p where LOWER(p.lastName) LIKE LOWER('%"+name+"%') OR LOWER(p.firstName) LIKE LOWER('%"+name+"%')";
        TypedQuery<Person> q = em.createQuery(query,Person.class);
        return q.getResultList();
    }
    /*******************************************************************************************************************
     * return the groups with the name contains param
     * @param name the searched word
     * @return list of Groups that contains the word
     ******************************************************************************************************************/
    @Override
    public List<Group> findGroupWithNameLike(String name){
        String query = "SELECT g FROM Group g where g.name LIKE '%"+name+"%'";
        TypedQuery<Group> q = em.createQuery(query,Group.class);
        return q.getResultList();
    }

    /*******************************************************************************************************************
     * Ajouter une personne
     * @param p person
     * @return a person
     ******************************************************************************************************************/
    @Override
    public Person addPerson(Person p) {
        em.persist(p);
        return p;
    }

    /*******************************************************************************************************************
     * add a group
     * @param g the added group
     ******************************************************************************************************************/
    @Override
    public Group addGroup(Group g) {
        em.persist(g);
        return (g);
    }


    /*******************************************************************************************************************
     * find a person by the id
     * @param id the person id
     * @return the person referred to the id
     ******************************************************************************************************************/
    @Override
    public Person findPerson(Long id) {
        Person p = em.find(Person.class, id);
        return p;
    }

    /*******************************************************************************************************************
     * find a person by it email
     * @param email the persons email
     * @return person
     ******************************************************************************************************************/
    @Override
    public Person findByEmail(String email){
        String query = "SELECT p FROM Person p where p.email = '" + email+"'";
        TypedQuery<Person> q = em.createQuery(query,Person.class);
        Person p ;
        if(q.getResultList().size()>0) {
            p = q.getResultList().get(0);
            p = em.find(Person.class, p.getId());
        }
        else{
            p =null;
        }
        return p ;
    }

    /*******************************************************************************************************************
     * find a group by the id group
     * @param id the id group
     * @return the group referring to the id
     ******************************************************************************************************************/
    @Override
    public Group findGroup(long id) {
        Group g = em.find(Group.class,id);
        return g;
    }

    /*******************************************************************************************************************
     * update a person
     * @param p the person we want to save
     ******************************************************************************************************************/
    @Override
    public void updatePerson(Person p) {
        em.merge(p);
    }

    /*******************************************************************************************************************
     * update Group
     * @param g the groupe that we want to save
     ******************************************************************************************************************/
    @Override
    public void updateGroup(Group g) {
        em.merge(g);
    }

    /*******************************************************************************************************************
     * remove a person from the DB
     * @param id the person to remove
     ******************************************************************************************************************/
    @Override
    public void removePerson(Long id) {
        em.remove(em.find(Person.class,id));
    }

    /*******************************************************************************************************************
     * remove a group from the  DB
     * @param id the group to remove
     ******************************************************************************************************************/
    @Override
    public void removeGroup(long id) {
        em.remove(em.find(Group.class,id));
    }

    /*******************************************************************************************************************
     * Close Entity Manger
     * @param em the entity manger we want to close
     ******************************************************************************************************************/
    private void closeEntityManager(EntityManager em) {
        if (em == null || !em.isOpen())
            return;

        var t = em.getTransaction();
        if (t.isActive()) {
            try {
                t.rollback();
            } catch (PersistenceException e) {
                e.printStackTrace(System.err);
            }
        }
        em.close();
    }

    /*******************************************************************************************************************
     * return the group having the same name as the param
     * @param name group name
     * @return group
     ******************************************************************************************************************/
    public Group findGroupByName(String name){
        String query = "SELECT g FROM Group g where g.name = '"+name+"'";
        TypedQuery<Group> q = em.createQuery(query,Group.class);
        if(q.getResultList().size()>0){
            return q.getResultList().get(0);
        }
        return null;
    }
    public Person findByResetPasswordToken(String token){
        String query = "SELECT p FROM Person p where p.resetPasswordToken = '" + token+"'";
        TypedQuery<Person> q = em.createQuery(query,Person.class);
        Person p ;
        if(q.getResultList().size()>0) {
            p = q.getResultList().get(0);
            p = em.find(Person.class, p.getId());
        }
        else{
            p =null;
        }
        return p;
    }
}
