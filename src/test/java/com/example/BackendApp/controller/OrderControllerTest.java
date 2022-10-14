//package com.example.BackendApp.controller;
//
//import com.example.BackendApp.entity.LapTopEntity;
//import com.example.BackendApp.entity.OrderEntity;
//import com.example.BackendApp.entity.UserEntity;
//import com.example.BackendApp.model.OrderModel;
//import com.example.BackendApp.repository.LapTopRepo;
//import com.example.BackendApp.repository.UserRepo;
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
//import javax.management.relation.Role;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class OrderControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    LapTopRepo lapTopRepo;
//
//    @Test
//    public void PostOrderTest() throws Exception {
//        OrderEntity order = new OrderEntity();
//        order.setId(1L);
//        order.setTitleOfProduct("Lenovo");
//        order.setPriceOfProduct("12222");
//        order.setLapTop(new LapTopEntity(1L, "Lenovo", "12222", (List<OrderEntity>) order));
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = writer.writeValueAsString(order);
//        mockMvc.perform(post("/api/v1/orders").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(requestJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteOrderTest() throws Exception {
//        this.mockMvc.perform(delete("/api/v1/orders/1"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//}