package com.example.learnunittest.controller;

import com.example.learnunittest.api.SomeApi;
import com.example.learnunittest.dto.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ApiController.class)
@AutoConfigureWebMvc
@Import(SomeApi.class)
public class ApiControllerTest {

    @MockBean
    private SomeApi api;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void init() {
        Mockito.when(api.getRate()).thenReturn(1200);
    }

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("http://localhost:8080/api/get")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("hello!")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void convertDollarsTest() throws Exception {
        String name = "John Doe";
        int dollars = 15;
        Request request = new Request(name, dollars);
        String reqToJson = new ObjectMapper().writeValueAsString(request);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("http://localhost:8080/api/convert-dollars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqToJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers
                        .jsonPath("$.result")
                        .value(dollars * 1200)
        ).andExpect(
                MockMvcResultMatchers
                        .jsonPath("$.name")
                        .value(name)
        ).andDo(MockMvcResultHandlers.print());
    }
}
