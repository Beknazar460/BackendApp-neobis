//package com.example.BackendApp.controller;
//
//import com.example.BackendApp.entity.UserEntity;
//import com.example.BackendApp.model.UserModel;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void getUsersTest() throws Exception {
//        this.mockMvc.perform(get("/api/v1/users"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getOneUserTest() throws Exception {
//        this.mockMvc.perform(get("/api/v1/users/1"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void postUserTest() throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(1L);
//        userEntity.setUserName("Beka");
//        userEntity.setUserPass("2134");
//        userEntity.setEmail("beka@gmail.com");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = writer.writeValueAsString(userEntity);
//        mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(requestJson))
//                        .andExpect(status().isOk());
//    }
//
//    @Test
//    public void putUserTest() throws Exception {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(1L);
//        userEntity.setEmail("brka@gmail.com");
//        userEntity.setUserName("Beka");
//        userEntity.setUserPass("12341234");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = writer.writeValueAsString(userEntity);
//        mockMvc.perform(put("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteUserTest() throws Exception {
//        this.mockMvc.perform(delete("/api/v1/users/1"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//}