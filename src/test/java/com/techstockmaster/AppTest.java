package com.techstockmaster;

import com.techstockmaster.app.App;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    // criar metodo para testar a conexão da classe de conexao
    @Test
     void testConexao() {
        App app = new App();
        app.getClass();
    }
}
