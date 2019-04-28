package com.ozzot.userstore.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.ozzot.userstore.entity.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ozzot.userstore.UserStoreApplication;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserStoreApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRestControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4))).andDo(print());
    }

    @Test
    public void getById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.birth").exists())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("name2"))
                .andExpect(jsonPath("$.birth").value(LocalDate.parse("1111-11-12").toString()))
                .andExpect(jsonPath("$.email").value("222@222.222"))
                .andDo(print());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4))).andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"name5\",\"birth\":[1111-11-15],\"email\":\"555@555.555\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.birth").exists())
                .andExpect(jsonPath("$.completed").exists())
                .andExpect(jsonPath("$.name").value("name5"))
                .andExpect(jsonPath("$.email").value("555@555.555"))
                .andExpect(jsonPath("$.birth").value(LocalDate.parse("1111-11-12").toString()))
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5))).andDo(print());
    }

    @Test
    public void update() throws Exception {

        User user = new User(5, "name5", "555_555@555.555", LocalDate.parse("1111-11-15"));

        mockMvc.perform(MockMvcRequestBuilders.patch("/todo/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"5\",\"name\":\"name555\",\"birth\":[1111-12-15],\"email\":\"555_555@555.555\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.birth").exists())
                .andExpect(jsonPath("$.completed").exists())
                .andExpect(jsonPath("$.name").value("name555"))
                .andExpect(jsonPath("$.email").value("555_555@555.555"))
                .andExpect(jsonPath("$.birth").value(LocalDate.parse("1111-12-15").toString()))
                .andDo(print());

    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/5/delete").accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4))).andDo(print());
    }
}