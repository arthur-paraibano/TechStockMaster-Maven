package com.techstockmaster.util;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/*
    A classe NumericFilter serve para restringir a entrada de texto em um campo de texto
    (JTextField) para garantir que apenas caracteres numéricos sejam inseridos e que o número
    total de dígitos não exceda um limite especificado.
    Vamos detalhar a finalidade e o funcionamento de cada parte da classe.
 */
public class NumericFilter extends DocumentFilter {

    private int maxDigits;

    NumericFilter(int maxDigits) {
        this.maxDigits = maxDigits;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        if (isNumeric(text) && (fb.getDocument().getLength() + text.length() <= maxDigits)) {
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isNumeric(text) && (fb.getDocument().getLength() - length + text.length() <= maxDigits)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isNumeric(String text) {
        return text.matches("\\d*");
    }

    public static void addNumericFilter(JTextField textField, int maxDigits) {
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        NumericFilter filter = new NumericFilter(maxDigits);
        document.setDocumentFilter(filter);
    }
}