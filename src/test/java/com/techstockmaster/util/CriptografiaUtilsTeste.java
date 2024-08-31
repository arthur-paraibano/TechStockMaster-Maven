package com.techstockmaster.util;

import org.junit.jupiter.api.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CriptografiaUtilsTeste {

    @DisplayName("1 - Teste Criptografar")
    @Test
    void testCriptografar() {
        // Metodo verificar se o arquivo de propriedades esta sendo criptografado
        CriptografiaUtils criptoUtils = new CriptografiaUtils();
        Properties props = criptoUtils.obterPropriedadesDescriptografadas("dbpessoal.properties.encrypted");
        Assertions.assertNotNull(props);
        System.out.println(props);
    }

    @DisplayName("2 - Teste Criar Criptografia")
    @Test
    void testCriarCriptografia() {
        CriptografiaUtils criptoUtils = new CriptografiaUtils();
        criptoUtils.criptografarArquivo("dbpessoal.properties");
    }

    @DisplayName("3 - Teste Descriptografar")
    @Test
    public void testConteudoLocal() {
        CriptografiaUtils criptoUtils = new CriptografiaUtils();
        Properties props = criptoUtils.obterPropriedadesDescriptografadas("dbpessoal.properties.encrypted");

        assertEquals("616853", props.getProperty("password"));
        assertEquals("root", props.getProperty("user"));
        assertEquals("jdbc:mysql://localhost:3306/bd_estoque?allowPublicKeyRetrieval=true&useSSL=false", props.getProperty("dburl"));
        assertEquals("false", props.getProperty("useSSL"));
    }

    @DisplayName("4 - Teste Descriptografar Lykos")
    @Test
    public void testConteudoLykos() {
        CriptografiaUtils criptoUtils = new CriptografiaUtils();
        Properties props = criptoUtils.obterPropriedadesDescriptografadas("dbLykos.properties.encrypted");

        assertEquals("integracao123", props.getProperty("password"));
        assertEquals("arthur", props.getProperty("user"));
        assertEquals("jdbc:mysql://192.168.0.199:3306/integracao?allowPublicKeyRetrieval=true&useSSL=false", props.getProperty("dburl"));
        assertEquals("false", props.getProperty("useSSL"));
    }
}
