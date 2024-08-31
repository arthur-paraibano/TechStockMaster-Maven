package com.techstockmaster.util;

import org.junit.jupiter.api.Test;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testa a classe {@link TransformFieldUppcase} para garantir que a transformação de texto para maiúsculas
 * e a limitação de comprimento funcionem conforme o esperado.
 */
public class TransformFieldUppcaseTeste {

    /**
     * Testa o método {@link TransformFieldUppcase#insertString(int, String, AttributeSet)} para garantir que o texto
     * inserido é transformado em maiúsculas.
     * <p>
     * Verifica se o texto inserido é convertido para maiúsculas corretamente, independentemente do caso
     * do texto original.
     * </p>
     *
     * @throws BadLocationException se ocorrer um erro ao inserir texto no documento
     */
    @Test
    void testInsertStringUpperCase() throws BadLocationException {
        TransformFieldUppcase doc = new TransformFieldUppcase(-1); // Sem limitação de comprimento
        doc.insertString(0, "hello", null);
        String result = doc.getText(0, doc.getLength());
        assertEquals("HELLO", result, "O texto inserido deve ser convertido para maiúsculas.");
    }

    /**
     * Testa o método {@link TransformFieldUppcase#insertString(int, String, AttributeSet)} quando o comprimento máximo é zero.
     * <p>
     * Verifica se o comprimento máximo de zero permite a inserção de texto e transforma o texto em maiúsculas.
     * </p>
     *
     * @throws BadLocationException se ocorrer um erro ao inserir texto no documento
     */
    @Test
    void testInsertStringZeroMaxLength() throws BadLocationException {
        TransformFieldUppcase doc = new TransformFieldUppcase(0); // Limitação de comprimento a 0 caracteres
        doc.insertString(0, "text", null);
        String result = doc.getText(0, doc.getLength());
        assertEquals("TEXT", result, "O texto inserido deve ser convertido para maiúsculas e inserido mesmo com comprimento máximo zero.");
    }

    /**
     * Testa o método {@link TransformFieldUppcase#insertString(int, String, AttributeSet)} para garantir que o texto
     * inserido é corretamente manipulado quando o comprimento máximo é negativo.
     * <p>
     * Verifica se o comprimento máximo negativo é tratado como ilimitado e o texto é inserido e transformado
     * para maiúsculas sem restrições de comprimento.
     * </p>
     *
     * @throws BadLocationException se ocorrer um erro ao inserir texto no documento
     */
    @Test
    void testInsertStringNegativeMaxLength() throws BadLocationException {
        TransformFieldUppcase doc = new TransformFieldUppcase(-1); // Sem limitação de comprimento
        doc.insertString(0, "longtext", null);
        String result = doc.getText(0, doc.getLength());
        assertEquals("LONGTEXT", result, "O texto inserido deve ser convertido para maiúsculas sem limitação de comprimento.");
    }
}
