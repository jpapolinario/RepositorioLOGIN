//É onde definimos os endpoints HTTP que recebem o JSON da internet, chamam o repositório e devolvem a resposta. a validação integrada de toda a arquitetura sem depender de telas.
package com.desafio.backend.controller;

// Importações especiais para testar as requisições HTTP de forma simples
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest // Inicia o Spring Boot por trás dos panos para o teste rodar real
@AutoConfigureMockMvc // Configura o simulador de requisições HTTP
public class TipoPessoaControllerTest {

    @Autowired
    private MockMvc mockMvc; // O simulador que vai "bater" no seu Controller

    @Test
    public void deveCriarEBuscarTipoPessoaComSucesso() throws Exception {
        
        // 1. TESTANDO O ENDPOINT DE CRIAÇÃO (POST)
        // Criamos um JSON idêntico ao que o front-end enviaria
        String jsonEnvio = "{\"descricao\": \"Cliente VIP\"}";

        mockMvc.perform(post("/api/tipo-pessoa") // Dispara um POST para a URL do seu Controller
                .contentType("application/json")
                .content(jsonEnvio))
                .andExpect(status().isOk()) // Garante que o servidor respondeu com sucesso (HTTP 200)
                .andExpect(jsonPath("$.id").exists()) // Garante que o banco gerou um ID automaticamente
                .andExpect(jsonPath("$.descricao").value("Cliente VIP")) // Garante que salvou a descrição certa
                .andExpect(jsonPath("$.estado").value(true)); // Garante que o estado padrão nasceu como true

        // 2. TESTANDO O ENDPOINT DE LISTAGEM (GET)
        // Agora listamos para ver se o registro que acabamos de criar aparece lá
        mockMvc.perform(get("/api/tipo-pessoa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Cliente VIP")); // Garante que o primeiro item da lista é o que criamos
    }
}
