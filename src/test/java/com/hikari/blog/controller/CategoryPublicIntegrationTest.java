package com.hikari.blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryPublicIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCategories_givenEmpty_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/public/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """, true
                ));
    }

    @Test
    void getCategories_givenOneCategory_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/public/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                        "id" : 1,
                        "name" : "General",
                        "slug" : "general"
                        ]
                        """, true
                ));
    }
}
