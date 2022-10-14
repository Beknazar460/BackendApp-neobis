//package com.example.BackendApp.controller;
//
//import com.example.BackendApp.entity.LapTopEntity;
//import com.example.BackendApp.entity.UserEntity;
//import com.example.BackendApp.model.LapTopModel;
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
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class LapTopControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    public void getLapTopsTest() throws Exception {
//        this.mockMvc.perform(get("/api/v1/laptops"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getOneLapTopTest() throws Exception {
//        this.mockMvc.perform(get("/api/v1/laptops/1"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void postLapTopTest() throws Exception {
//        LapTopEntity lapTopEntity = new LapTopEntity();
//        lapTopEntity.setId(1L);
//        lapTopEntity.setTitle("HP");
//        lapTopEntity.setPrice("12222");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = writer.writeValueAsString(lapTopEntity);
//        mockMvc.perform(post("/api/v1/laptops").contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(requestJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void putLaptopTest() throws Exception {
//        LapTopEntity lapTopEntity = new LapTopEntity();
//        lapTopEntity.setId(1L);
//        lapTopEntity.setTitle("Lenovo");
//        lapTopEntity.setPrice("12444");
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = writer.writeValueAsString(lapTopEntity);
//        mockMvc.perform(put("/api/v1/laptops/1").contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(requestJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteLapTopTest() throws Exception {
//        this.mockMvc.perform(delete("/api/v1/laptops/1"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//}