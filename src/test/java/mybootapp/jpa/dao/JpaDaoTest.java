package mybootapp.jpa.dao;

import mybootapp.jpa.model.Group;
import mybootapp.jpa.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JpaDaoTest {

    @Autowired
    JpaDao jpaDao;

    /*
    //**************** Created Address *******************
    @Autowired
    Address address = new Address("5, rue ,Joliette","Marseille","france");

    //****************** Created persons ******************
    @Autowired
    Person p1 = new Person("youssef","HAOUES",address,"houasyoussef@gmail.com",null,new Date(2000,5,8),"pass1",null);
    @Autowired
    Person p2 = new Person("abdel","BENS",address,"AbdelBens@gmail.com",null, new Date(1999,12,5),"pass2",null);
    @Autowired
    Person p3 = new Person("jean","lucas",address,"jeanlucas@gmail.com",null, new Date(1996,5,12), "pass3",null);

    //**************** Created Groups *******************
    @Autowired
    Group g1 = new Group("g1");
    @Autowired
    Group g2 = new Group("g2");
    @Autowired
    Group g3 = new Group("g3");
    */


    @Test
    void addAndFindPersons() {
        Person p1 = new Person("youssef","HAOUES","houasyoussef000008@gmail.com",null,new Date(2000, Calendar.JUNE,8),"pass1",null);
        p1 = jpaDao.addPerson(p1);
        assertNotNull(p1.getId());
        Person test = jpaDao.findPerson(p1.getId());
        assertEquals(test.getFirstName(), p1.getFirstName());
    }

    @Test
    void addAndFindGroups() {
        Group g1 = new Group("g137321643141");
        g1 = jpaDao.addGroup(g1);
        Group test = jpaDao.findGroup(g1.getId());
        assertEquals(g1.getId(),test.getId(),"id found = id created");

    }

    @Test
    void addPersonsToGroups(){
        Person p1 = new Person("youssef","HAOUES","houasyoussef673216@gmail.com",null,new Date(2000,5,8),"pass1",null);
        p1 = jpaDao.addPerson(p1);
        Group g1 = new Group("g3574641");
        g1 = jpaDao.addGroup(g1);
        g1.addPerson(p1);
        assertEquals(p1.getGroup().getId(),g1.getId());
    }

    @Test
    void findAllGroups() {
        List<Group> groups1 = jpaDao.findAllGroups();
        Group g1 = new Group("g648");
        g1 = jpaDao.addGroup(g1);
        List<Group> groups = jpaDao.findAllGroups();
        assertEquals(groups1.size()+1,groups.size());

    }

    @Test
    void findAllPersons() {
        List<Person> persons = jpaDao.findAllPersons();
        Person p1 = new Person("youssef","HAOUES","houasyoussef123@gmail.com",null,new Date(2000,5,8),"pass1",null);
        Person p2 = new Person("abdel","BENS","AbdelBens123@gmail.com",null, new Date(1999,12,5),"pass2",null);
        Person p3 = new Person("jean","lucas","jeanlucas@gmail.com",null, new Date(1996,5,12), "pass3",null);
        p1 = jpaDao.addPerson(p1);
        p2 = jpaDao.addPerson(p2);
        p3 = jpaDao.addPerson(p3);

        assertEquals( persons.size()+3,jpaDao.findAllPersons().size());

    }

    @Test
    void updatePerson() {
        Person p1 = new Person("youssef","HAOUES","houasyoussef111@gmail.com",null,new Date(2000,5,8),"pass1",null);
        p1 = jpaDao.addPerson(p1);
        assertEquals("youssef", p1.getFirstName());
        p1.setFirstName("mahmoud");
        assertEquals("mahmoud",p1.getFirstName());
    }

    @Test
    void updateGroup() {
        Group g1 = new Group("g99");
        g1 = jpaDao.addGroup(g1);
        assertEquals("g99",g1.getName());
        g1.setName("g62");
        assertEquals("g62",g1.getName());
    }

    @Test
    void removePerson() {
        Person p1 = new Person("youssef","HAOUES","houasyoussef01@gmail.com",null,new Date(2000,5,8),"pass1",null);
        p1 = jpaDao.addPerson(p1);
        var id = p1.getId();
        assertEquals(jpaDao.findPerson(p1.getId()).getId(), id);
        jpaDao.removePerson(p1.getId());
        assertNull(jpaDao.findPerson(id));

    }

    @Test
    void removeGroup() {
        Group g1 = new Group("g65");
        g1 = jpaDao.addGroup(g1);
        long id= g1.getId();
        assertEquals(jpaDao.findGroup(g1.getId()).getId(), id);
        jpaDao.removeGroup(g1.getId());
        assertNull(jpaDao.findGroup(id));
    }

    @Test
    void Add1000Person100Groups(){
        int i = 0;

        for(int j=0 ;j<=100; j++){
            i= i+j;
            String nameGroup= "k" + String.valueOf(i);
            jpaDao.addGroup(new Group(nameGroup));
        }
        i = 0;
        for (int x=0; x<=1000; x++){
            i = i+x;
            String emailPerson = "kk" + String.valueOf(i);
            jpaDao.addPerson(new Person(emailPerson,emailPerson, Set.of("USER"),null));
        }
        List<Group> groups = jpaDao.findAllGroups();
        List<Person> persons = jpaDao.findAllPersons();
        assertTrue(groups.size()>99 && persons.size()>999);
        System.out.println( "Groups size ==> " + groups.size());
        System.out.println( "Persons size ==> " + persons.size());
    }

    /*
    @Test
    public void testParalleleThread(){
        Person p1 = new Person("youssef","HAOUES","houasyoussef11@gmail.com",null,new Date(2000,5,8),"pass1",null);
        Person p2 = new Person("abdel","BENS","AbdelBens11@gmail.com",null, new Date(1999,12,5),"pass2",null);

        Thread t1 = new Thread(()-> jpaDao.addPerson(p1));
        Thread t2 = new Thread(()->jpaDao.addPerson(p2));

        t1.run();
        System.out.println("person added");
        t2.run();
        System.out.println("person 2 added");

    }

     */


}