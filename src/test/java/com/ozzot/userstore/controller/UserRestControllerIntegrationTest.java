package com.ozzot.userstore.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ozzot.userstore.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

  @Test
  public void getAll() throws Exception {
    mockMvc.perform(get("/api/user"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(16))).andDo(print());
  }

  @Test
  public void getById() throws Exception {

    mockMvc.perform(get("/api/user/2").accept(
        MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.email").exists())
        .andExpect(jsonPath("$.birth").exists())
        .andExpect(jsonPath("$.id").value(2))
        .andExpect(jsonPath("$.name").value("name2"))
        .andExpect(
            jsonPath("$.birth").value(LocalDate.parse("1111-11-12").toString()))
        .andExpect(jsonPath("$.email").value("222@222.222"))
        .andDo(print());
  }

  @Test
  public void add() throws Exception {
    mockMvc.perform(get("/api/user").accept(
        MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(17))).andDo(print());

    mockMvc.perform(post("/api/user/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"name3\",\"email\":\"333@333.333\",\"birth\":\"2019-06-28\"}"))
        .andDo(print());

    mockMvc.perform(get("/api/user").accept(
        MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(18))).andDo(print());

    mockMvc.perform(get("/api/user/18").accept(
        MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(18))
        .andExpect(jsonPath("$.name").value("name3"))
        .andExpect(
            jsonPath("$.birth").value("2019-06-28"))
        .andExpect(jsonPath("$.email").value("333@333.333"))
        .andDo(print());
  }

  @Test
  public void update() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.put("/user/18/update")
        .contentType(MediaType.APPLICATION_JSON)
        .param("name", "name555")
        .param("email", "555_555@555.555")
        .param("birth", "2019-06-29"))
        .andDo(print());

    mockMvc.perform(get("/api/user/18").accept(
        MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(18))
        .andExpect(jsonPath("$.name").value("name555"))
        .andExpect(jsonPath("$.email").value("555_555@555.555"))
        .andExpect(
    jsonPath("$.birth").value(LocalDate.parse("2019-06-29").toString()))
        .andDo(print());
  }

  @Test
  public void delete() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/18/delete").accept(
        MediaType.APPLICATION_JSON))
        .andDo(print());

    mockMvc.perform(get("/api/user").accept(
        MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(17))).andDo(print());
  }
}