package com.techstockmaster.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EncryptTeste {

    @Test
    void testEncrypt() {
        // Dada uma senha real
        String password = "senha123";

        // Quando encriptografar a senha
        String encrypt = Encrypt.encriptografat(password);

        // Então o resultado não deve ser nulo
        Assertions.assertNotNull(encrypt);

        // E o valor criptografado deve ser diferente da senha original
        Assertions.assertNotEquals(password, encrypt);

        // Opcional: Verificar o comprimento do resultado (32 caracteres para MD5)
        Assertions.assertEquals(32, encrypt.length());
    }
}
