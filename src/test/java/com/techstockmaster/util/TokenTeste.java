package com.techstockmaster.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa a classe {@link Token} para garantir que o método de geração de tokens ({@link Token#generator()})
 * funcione corretamente e produza tokens que atendam aos critérios esperados.
 */
public class TokenTeste {

    /**
     * Testa o método {@link Token#generator()} para garantir que o token gerado tenha o comprimento correto.
     * <p>
     * Verifica se o comprimento do token gerado é exatamente igual ao valor esperado (8 caracteres).
     * </p>
     */
    @Test
    void testTokenLength() {
        String token = Token.generator();

        // Verifica se o comprimento do token é 8 caracteres
        assertEquals(8, token.length(), "O comprimento do token deve ser 8 caracteres.");
    }

    /**
     * Testa o método {@link Token#generator()} para garantir que o token gerado contém apenas caracteres alfanuméricos em maiúsculas.
     * <p>
     * Verifica se o token gerado contém apenas caracteres de '0' a '9' e de 'A' a 'Z'.
     * </p>
     */
    @Test
    void testTokenCharacters() {
        String token = Token.generator();

        // Verifica se o token contém apenas caracteres válidos (A-Z, 0-9)
        assertTrue(token.matches("[A-Z0-9]{8}"), "O token deve conter apenas caracteres alfanuméricos em maiúsculas.");
    }

    /**
     * Testa o método {@link Token#generator()} para garantir que o token gerado não está vazio.
     * <p>
     * Verifica se o token gerado não é uma string vazia.
     * </p>
     */
    @Test
    void testTokenNotEmpty() {
        String token = Token.generator();

        // Verifica se o token não está vazio
        assertFalse(token.isEmpty(), "O token não deve estar vazio.");
    }

    /**
     * Testa o método {@link Token#generator()} para garantir que o token gerado é realmente aleatório.
     * <p>
     * Gera vários tokens e verifica se pelo menos dois deles são diferentes, para garantir que o método é
     * suficientemente aleatório.
     * </p>
     */
    @Test
    void testTokenRandomness() {
        String token1 = Token.generator();
        String token2 = Token.generator();

        // Verifica se dois tokens gerados são diferentes
        assertNotEquals(token1, token2, "Dois tokens gerados consecutivamente devem ser diferentes.");
    }
}