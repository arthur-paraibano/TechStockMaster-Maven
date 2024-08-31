package com.techstockmaster.util.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DatabaseSistTeste {

    @BeforeAll
    static void config() {
        DatabaseSist.testeConnection();
    }

    /*
     * // Executa ANTES de qualquer teste que existir na classe:
     *
     * @BeforeEach
     * void insereDados(){
     * DatabaseSist db = new DatabaseSist();
     * db.testeConnection();
     * }
     */

    @Test
    void testConexao() {
        Assertions.assertTrue(true);
    }

    /*
     * // Executa DEPOIS de qualquer teste que existir na classe:
     *
     * @AfterEach
     * static void fechaConexao(){
     * DatabaseSist db = new DatabaseSist();
     * db.closeConnection(DatabaseSist.getConnection());
     * }
     */

    @AfterAll
    static void close() {
        DatabaseSist.closeConnection(DatabaseSist.getConnection());
    }
}
