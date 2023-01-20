package com.spring.program.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.program.project.ProjectApplication;
import com.spring.program.project.controller.utils.TestData;
import com.spring.program.project.entity.User;
import com.spring.program.project.pojo.LoginRequest;
import com.spring.program.project.repository.RoleRepository;
import com.spring.program.project.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author DacaP
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Order(1)
public class UserRestControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        roleRepository.save(TestData.admin);
        roleRepository.save(TestData.user);
        userRepository.save(TestData.adminUser);
        userRepository.save(TestData.firstUser);
        userRepository.save(TestData.secondUser);
        userRepository.save(TestData.userForDelete);
    }

    @Test
    public void getUsers_whenAdminTryToGetAllUsers_thenStatus200() throws Exception {
        String username = "admin";
        String password = "admin";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        mockMvc.perform(MockMvcRequestBuilders.get("/users").header("Authorization", authToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getUsers_whenUserTryToGetAllUsers_thenStatus403() throws Exception {
        String username = "user";
        String password = "user";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        mockMvc.perform(MockMvcRequestBuilders.get("/users").header("Authorization", authToken))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getUsers_whenUnknownTryToGetAllUsers_thenStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getUserById_whenAdminTryToGetUserById_thenStatus200AndCorrectValue() throws Exception {
        String username = "admin";
        String password = "admin";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1").header("Authorization", authToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TestData.adminUser.getId()))
                .andExpect(jsonPath("$.username").value(TestData.adminUser.getUsername()))
                .andExpect(jsonPath("$.password").value(TestData.adminUser.getPassword()))
                .andExpect(jsonPath("$.email").value(TestData.adminUser.getEmail()))
                .andExpect(jsonPath("$.birthday").value(TestData.adminUser.getBirthday().toString()))
                .andExpect(jsonPath("$.firstName").value(TestData.adminUser.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(TestData.adminUser.getLastName()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getUserById_whenUserTryToGetUserById_thenStatus403() throws Exception {
        String username = "user";
        String password = "user";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1").header("Authorization", authToken))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getUserById_whenUnknownTryToGetUserById_thenStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void addNewUser_whenAdminTryToCreateUser_thenStatus200AndCorrectValue() throws Exception {
        String username = "admin";
        String password = "admin";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("Authorization", authToken)
                        .content(getRequestJson(TestData.createUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(TestData.createUser.getUsername()))
                .andExpect(jsonPath("$.email").value(TestData.createUser.getEmail()))
                .andExpect(jsonPath("$.birthday").value(TestData.createUser.getBirthday().toString()))
                .andExpect(jsonPath("$.firstName").value(TestData.createUser.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(TestData.createUser.getLastName()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void addNewUser_whenUserTryToCreateUser_thenStatus403() throws Exception {
        String username = "user";
        String password = "user";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("Authorization", authToken)
                        .content(getRequestJson(TestData.createUser)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void addNewUser_whenUnknownTryToCreateUser_thenStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getRequestJson(TestData.createUser)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateUser_whenAdminTryToUpdateUser_thenStatus200AndCorrectValue() throws Exception {
        String username = "admin";
        String password = "admin";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("Authorization", authToken)
                        .content(getRequestJson(TestData.updateUser)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(result.getResponse().getContentAsByteArray(), User.class);
        Assertions.assertTrue(userRepository.findAll().contains(user));
    }

    @Test
    public void updateUser_whenUserTryToUpdateUser_thenStatus403() throws Exception {
        String username = "user";
        String password = "user";

        String tokenType = getTokenType(username, password);
        String jwtToken = getJwtTokenByUsernameAndPassword(username, password);
        String authToken = tokenType + " " + jwtToken;

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("Authorization", authToken)
                        .content(getRequestJson(TestData.createUser)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateUser_whenUnknownTryToUpdateUser_thenStatus403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getRequestJson(TestData.createUser)))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }
    
    private String getTokenType(String username, String password) throws Exception {
        for (String data : getContentByUsernameAndPassword(username, password)) {
            if (data.contains("\"type\"")) {
                String jwtToken = data.split(":")[1];
                return jwtToken.substring(1, jwtToken.length() - 1);
            }
        }
        return null;
    }

    private String getJwtTokenByUsernameAndPassword(String username, String password) throws Exception {
        for (String data : getContentByUsernameAndPassword(username, password)) {
            if (data.contains("\"token\"")) {
                String jwtToken = data.split(":")[1];
                return jwtToken.substring(1, jwtToken.length() - 1);
            }
        }
        return null;
    }

    private String[] getContentByUsernameAndPassword(String username, String password) throws Exception {
        LoginRequest loginRequest = new LoginRequest(username, password);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users/authenticate")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(getRequestJson(loginRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        return result.getResponse().getContentAsString().split(",");
    }

    private String getRequestJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}