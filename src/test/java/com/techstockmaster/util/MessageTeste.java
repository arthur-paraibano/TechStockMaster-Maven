package com.techstockmaster.util;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

public class MessageTeste {

    @Test
    void testResourcePath() {
        // Verifica se o recurso foi encontrado
        URL resourceUrl = Message.class.getResource("/com/techstockmaster/resources/loginTech.png");
        assertNotNull(resourceUrl, "O recurso não foi encontrado no caminho especificado");

        // Verifica se o ícone pode ser carregado a partir do recurso
        Icon icon = new ImageIcon(resourceUrl);
        assertNotNull(icon, "O ícone não pôde ser carregado a partir do recurso.");

        // Testa o método errorX sem lançar exceções
        assertDoesNotThrow(() -> Message.sucess(null, "Sucesso no teste"));
    }

    @Test
    void listResourceFiles() {
        java.net.URL resource = Message.class.getResource("/com/techstockmaster/resources/");
        if (resource != null) {
            try {
                java.nio.file.Path path = java.nio.file.Paths.get(resource.toURI());
                try (java.nio.file.DirectoryStream<java.nio.file.Path> stream = java.nio.file.Files
                        .newDirectoryStream(path)) {
                    for (java.nio.file.Path entry : stream) {
                        System.out.println(entry.getFileName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Diretório de recursos não encontrado.");
        }
    }
}