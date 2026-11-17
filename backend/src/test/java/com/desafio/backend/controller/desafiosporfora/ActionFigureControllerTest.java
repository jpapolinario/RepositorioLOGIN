package com.desafio.backend.controller.desafiosporfora;

//Libera os comandos HTTP simulados como post(), get(), put(), delete()
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// Libera as validações de resposta, como status().isOk() e jsonPath()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; //Habilitam o ambiente do Spring Boot e configuram o simulador de requisições web.
import org.springframework.boot.test.context.SpringBootTest;
//Habilitam o ambiente do Spring Boot e configuram o simulador de requisições web.
import org.springframework.jdbc.core.JdbcTemplate; //Ferramenta do Spring para executar comandos diretos no banco de dados via SQL (usada para limpar o banco).
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc // configura o MockMvc, que dispara as requisições para os controller sem
                      // precisar abrir a aplicação de verdade
public class ActionFigureControllerTest {

        @Autowired
        private MockMvc mockMvc; // simular as requisições HTTP

        @Autowired
        private JdbcTemplate jdbcTemplate; // acesso direto ao SQL

        @BeforeEach // Execute isso obrigatoriamente antes de cada método @Test
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