package com.desafio.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionFigureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void limparBanco() {
        jdbcTemplate.execute("TRUNCATE TABLE action_figure, tipo_action_figure RESTART IDENTITY CASCADE");
    }

    @Test
    public void deveCadastrarEConsultarTipoActionFigure() throws Exception {
        String jsonTipo = "{\"descricao\": \"Dragon Ball Z\", \"excluido\": false}";

        mockMvc.perform(post("/api/tipo-action-figure")
                .contentType("application/json")
                .content(jsonTipo))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/tipo-action-figure"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void deveCadastrarActionFigureVinculada() throws Exception {
        String jsonTipo = "{\"descricao\": \"Naruto\", \"excluido\": false}";
        
        mockMvc.perform(post("/api/tipo-action-figure")
                .contentType("application/json")
                .content(jsonTipo))
                .andExpect(status().isOk());

        String jsonActionFigure = "{\"nome\": \"Sasuke Uchiha\", \"excluido\": false, \"tipoActionFigure\": {\"id\": 1}}";

        mockMvc.perform(post("/api/action-figure")
                .contentType("application/json")
                .content(jsonActionFigure))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/action-figure"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}