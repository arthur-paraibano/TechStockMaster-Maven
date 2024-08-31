package com.techstockmaster.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Uma classe personalizada que estende {@link PlainDocument} para transformar automaticamente
 * todas as entradas em texto maiúsculo e limitar o comprimento do texto.
 * <p>
 * Esta classe é útil para campos de texto onde o texto deve ser exibido em maiúsculas
 * e onde o comprimento do texto precisa ser controlado.
 * </p>
 */
public class TransformFieldUppcase extends PlainDocument {

    /**
     * O comprimento máximo permitido para o texto.
     * Se o comprimento máximo for menor ou igual a 0, não haverá restrição de comprimento.
     */
    private final int iMaxLength;

    /**
     * Cria uma nova instância de {@code TransformFieldUppcase} com o comprimento máximo especificado.
     *
     * @param maxlen o comprimento máximo permitido para o texto. Se for menor ou igual a 0, não haverá restrição de comprimento.
     */
    public TransformFieldUppcase(int maxlen) {
        this.iMaxLength = maxlen;
    }

    /**
     * Insere uma string no documento, convertendo o texto para maiúsculas e respeitando o comprimento máximo.
     * <p>
     * Se o comprimento total do texto após a inserção exceder o comprimento máximo definido,
     * apenas a quantidade de texto necessária para atingir o comprimento máximo será inserida.
     * </p>
     *
     * @param offset a posição no documento onde a string deve ser inserida
     * @param str a string a ser inserida
     * @param attr atributos a serem aplicados à string inserida
     * @throws BadLocationException se a posição especificada estiver fora do intervalo do documento
     */
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (this.iMaxLength <= 0) {
            // Se o comprimento máximo for <= 0, não há restrição de comprimento
            super.insertString(offset, str.toUpperCase(), attr);
            return;
        }
        int ilen = getLength() + str.length();
        if (ilen <= this.iMaxLength) {
            // Insere a string convertida para maiúsculas, respeitando o comprimento máximo
            super.insertString(offset, str.toUpperCase(), attr);
        }
    }
}