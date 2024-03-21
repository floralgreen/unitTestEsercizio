package esercizidevelhope.unitTestEsercizio;

import com.fasterxml.jackson.databind.ObjectMapper;
import esercizidevelhope.unitTestEsercizio.controllers.UtenteController;
import esercizidevelhope.unitTestEsercizio.entities.Utente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UtenteControllerTest {

    @Autowired
    private UtenteController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    void createUser() throws Exception {
        Utente user = new Utente();
        user.setId(1L);
        user.setName("Filippo");
        user.setSurname("Rossi");

        String userJSON = objectMapper.writeValueAsString(user);

        MvcResult resultActions = this.mockMvc.perform(post("/utente/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void readUserList() throws Exception {
        createUser();
        MvcResult result = this.mockMvc.perform(get("/utente/all"))
                .andDo(print()).andReturn();

        List<Utente> userFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertThat(userFromResponseList.size()).isNotZero();
    }

    @Test
    void getUser() throws Exception {
        Long studentId = 1L;
        createUser();

        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/utente/find/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(studentId)).andReturn();
    }

    @Test
    void updateStudentById() throws Exception {
        Long userId = 1L;
        createUser();
        Utente updatedUser = new Utente(userId, "Updated", "Name");
        String userJSON = objectMapper.writeValueAsString(updatedUser);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/utente/edit/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON).content(userJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertNotNull(content);
    }

    @Test
    void deleteUser() throws Exception {
        Long id = 1L;
        createUser();

        MvcResult result = mockMvc.perform(delete("/utente/delete/{id}", id))
                .andExpect(status().isOk()).andReturn();


    }
}