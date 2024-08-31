package com.techstockmaster.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumericFilterTeste {

    private JTextField textField;
    private NumericFilter filter;

    @BeforeEach
    void setUp() {
        textField = new JTextField();
        NumericFilter.addNumericFilter(textField, 5); // Limita a 5 dígitos
    }

    @Test
    void testInsertNumericText() throws BadLocationException {
        // Testa a inserção de texto numérico
        textField.setText(""); // Limpa o campo de texto
        textField.getDocument().insertString(0, "12345", null);

        assertEquals("12345", textField.getText(), "O texto inserido deve ser '12345'");
    }

    @Test
    void testInsertNonNumericText() throws BadLocationException {
        // Testa a tentativa de inserção de texto não numérico
        textField.setText(""); // Limpa o campo de texto
        textField.getDocument().insertString(0, "abc", null);

        assertEquals("", textField.getText(), "O texto não numérico não deve ser inserido");
    }

    @Test
    void testReplaceWithNumericText() throws BadLocationException {
        // Testa a substituição com texto numérico
        textField.setText("12"); // Configura texto inicial
        textField.getDocument().insertString(2, "345", null); // Substitui o texto existente

        assertEquals("12345", textField.getText(), "O texto deve ser '12345' após a substituição");
    }

    @Test
    void testReplaceWithNonNumericText() throws BadLocationException {
        // Testa a substituição com texto não numérico
        textField.setText("123"); // Configura texto inicial
        textField.getDocument().insertString(0, "ab", null); // Tenta substituir com texto não numérico

        assertEquals("123", textField.getText(), "Texto não numérico não deve substituir o texto existente");
    }
}