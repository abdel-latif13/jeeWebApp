package mybootapp.web;

import mybootapp.Starter;
import mybootapp.jpa.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
@AutoConfigureMockMvc
public class TestWebApp {

    @Autowired
    private MockMvc mvc;

    private Person person;


    //GROUP Controleur
    @Test
    public void testGroupList()throws Exception{
        mvc.perform(get("/group/list"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("groupList"))
                //vérifier le modèle
                .andExpect(model().attributeExists("groups"));
    }
    @Test
    public void testEditGroup() throws Exception {
        mvc.perform(get("/group/edit"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk());

    }
    @Test
    public void testSaveGroup() throws Exception {
        mvc.perform(post("/group/edit"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk());

    }
    @Test
    public void testFindGroup() throws Exception {
        mvc.perform(get("/group/find"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("groupList"))
                //vérifier le modèle
                .andExpect(model().attributeExists("groups"));

    }
    @Test
    public void testaddPersonToGroup() throws Exception {
        mvc.perform(get("/group/addPersonToGroup"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("groupForm"));



    }
    @Test
    public void testRemovePersonFromGroup() throws Exception {
        mvc.perform(get("/group/removePersonToGroup"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("groupForm"));

    }

    //Person Controleur*************************************************************************************************
    @Test
    public void testPersonList()throws Exception{
        mvc.perform(get("/person/list"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("personList"))
                //vérifier le modèle
                .andExpect(model().attributeExists("persons"));
    }
    @Test
    public void testEditPerson()throws Exception{
        mvc.perform(get("/person/edit"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testSavePerson(){

    }
    @Test
    public void testFindPerson() throws Exception {
        mvc.perform(get("/person/find"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("personList"))
                //vérifier le modèle
                .andExpect(model().attributeExists("persons"));

    }
    @Test
    public void testEditMePerson() throws Exception {
        mvc.perform(get("/person/editMe"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("personForm"))
                //vérifier le modèle
                .andExpect(model().attributeExists("person"));

    }
    @Test
    public void testMyGroupPerson() throws Exception {
        mvc.perform(get("/person/myGroup"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le nom de la vue
                .andExpect(view().name("personList"))
                //vérifier le modèle
                .andExpect(model().attributeExists("persons"));

    }

    //Login Controleur**************************************************************************************************


    @Test
    public void testlogin()throws Exception{
        Person person = new Person("mail@mail.com","mail", Set.of("USER"),null);
        RequestBuilder builder = post("/login")
                .param("username",person.getEmail())
                .param("password",person.getPassword());
        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());

    }
    //User Controleur****************************************************************************************************
    @Test
    public void testUserLogin() throws Exception {
        mvc.perform(get("/user/login"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le modèle
                .andExpect(model().attributeExists("message"));

    }
    @Test
    public void testUserLogout() throws Exception {
        mvc.perform(get("/user/logout"))
                //afficher
                .andDo(print())
                //vérifier le status
                .andExpect(status().isOk())
                //vérifier le modèle
                .andExpect(model().attributeExists("message"));

    }




}
