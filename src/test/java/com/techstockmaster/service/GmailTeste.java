package com.techstockmaster.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GmailTeste {

    private final Gmail gmail = new Gmail();

    @Test
    void envioDeEmail() {
        String emailTo = "teste@gmail.com";
        String token = "123456";
        gmail.shippingGmail(emailTo, token);
    }
}
