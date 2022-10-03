package com.example.BackendApp.controller;

import com.example.BackendApp.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getUsersTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOneUserTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void postUserTest() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setUserName("Beka");
        userModel.setEmail("beka@gmail.com");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = writer.writeValueAsString(userModel);
        mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }


}