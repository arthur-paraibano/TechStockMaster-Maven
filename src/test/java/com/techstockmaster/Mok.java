package com.techstockmaster;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class Mok {
    @Mock
    private List<String> mok;

    @Test
    void testeConnection() {
        Mockito.when(mok.get(0)).thenReturn("Teste");
        assertEquals("Teste", mok.get(0));
    }
}
