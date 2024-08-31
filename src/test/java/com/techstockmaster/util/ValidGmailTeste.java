package com.techstockmaster.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testa a classe {@link ValidGmail} para garantir que o método {@link ValidGmail#isValidEmail(String)}
 * valida corretamente os endereços de e-mail de acordo com o formato especificado.
 */
public class ValidGmailTeste {

    /**
     * Testa o método {@link ValidGmail#isValidEmail(String)} para garantir que um e-mail no formato correto é validado como verdadeiro.
     * <p>
     * Verifica se o método retorna {@code true} para e-mails que atendem ao formato padrão especificado pela expressão regular.
     * </p>
     */
    @Test
    void testValidEmailFormat() {
        assertTrue(ValidGmail.isValidEmail("example@gmail.com"), "O e-mail 'example@gmail.com' deve ser considerado válido.");
        assertTrue(ValidGmail.isValidEmail("user.name+tag+sorting@example.com"), "O e-mail 'user.name+tag+sorting@example.com' deve ser considerado válido.");
        assertTrue(ValidGmail.isValidEmail("user.name@subdomain.example.com"), "O e-mail 'user.name@subdomain.example.com' deve ser considerado válido.");
    }

    /**
     * Testa o método {@link ValidGmail#isValidEmail(String)} para garantir que um e-mail no formato incorreto é validado como falso.
     * <p>
     * Verifica se o método retorna {@code false} para e-mails que não atendem ao formato especificado pela expressão regular.
     * </p>
     */
    @Test
    void testInvalidEmailFormat() {
        assertFalse(ValidGmail.isValidEmail("plainaddress"), "O e-mail 'plainaddress' deve ser considerado inválido.");
        assertFalse(ValidGmail.isValidEmail("missingdomain@.com"), "O e-mail 'missingdomain@.com' deve ser considerado inválido.");
        assertFalse(ValidGmail.isValidEmail("@missingusername.com"), "O e-mail '@missingusername.com' deve ser considerado inválido.");
        assertFalse(ValidGmail.isValidEmail("user@domain,com"), "O e-mail 'user@domain,com' deve ser considerado inválido.");
    }

    /**
     * Testa o método {@link ValidGmail#isValidEmail(String)} para garantir que um e-mail com caracteres inválidos é validado como falso.
     * <p>
     * Verifica se o método retorna {@code false} para e-mails que contêm caracteres não permitidos na parte local ou no domínio.
     * </p>
     */
    @Test
    void testEmailWithInvalidCharacters() {
        assertFalse(ValidGmail.isValidEmail("user@domain#example.com"), "O e-mail 'user@domain#example.com' deve ser considerado inválido.");
        assertFalse(ValidGmail.isValidEmail("user@domain..com"), "O e-mail 'user@domain..com' deve ser considerado inválido.");
        assertFalse(ValidGmail.isValidEmail("user@domain.c"), "O e-mail 'user@domain.c' deve ser considerado inválido.");
    }

    /**
     * Testa o método {@link ValidGmail#isValidEmail(String)} para garantir que e-mails com subdomínios sejam corretamente validados.
     * <p>
     * Verifica se o método retorna {@code true} para e-mails com subdomínios válidos.
     * </p>
     */
    @Test
    void testEmailWithSubdomains() {
        assertTrue(ValidGmail.isValidEmail("user@subdomain.domain.com"), "O e-mail 'user@subdomain.domain.com' deve ser considerado válido.");
        assertTrue(ValidGmail.isValidEmail("user.name@subdomain.domain.com"), "O e-mail 'user.name@subdomain.domain.com' deve ser considerado válido.");
    }
}