package com.techstockmaster.util;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa a classe {@link OptionPaneCreator} para garantir que os componentes {@link JOptionPane}
 * e {@link JDialog} são criados corretamente com as propriedades esperadas.
 */
public class OptionPaneCreatorTeste {

    /**
     * Testa a criação de um {@link JOptionPane} usando {@link OptionPaneCreator}.
     * <p>
     * Verifica se o {@link JOptionPane} criado não é nulo, se a mensagem exibida está correta,
     * o tipo de mensagem é {@link JOptionPane#QUESTION_MESSAGE}, e o tipo de opção é
     * {@link JOptionPane#YES_NO_OPTION}.
     * </p>
     */
    @Test
    void testOptionPaneCreate() {
        OptionPaneCreator optionPaneCreator = new OptionPaneCreator();
        JOptionPane optionPane = optionPaneCreator.createOptionPane();

        // Verifica se o JOptionPane não é nulo
        assertNotNull(optionPane, "O JOptionPane não deve ser nulo.");
        // Verifica se a mensagem exibida é a esperada
        assertEquals("Tem certeza que deseja Salvar?", optionPane.getMessage(), "A mensagem exibida deve ser 'Tem certeza que deseja Salvar?'.");
        // Verifica o tipo de mensagem
        assertEquals(JOptionPane.QUESTION_MESSAGE, optionPane.getMessageType(), "O tipo de mensagem deve ser QUESTION_MESSAGE.");
        // Verifica o tipo de opção
        assertEquals(JOptionPane.YES_NO_OPTION, optionPane.getOptionType(), "O tipo de opção deve ser YES_NO_OPTION.");
    }

    /**
     * Testa a criação de um {@link JDialog} com um {@link JOptionPane} usando {@link OptionPaneCreator}.
     * <p>
     * Verifica se o {@link JDialog} criado não é nulo, se o título do diálogo é o esperado, e se a
     * operação de fechamento do diálogo é {@link WindowConstants#DISPOSE_ON_CLOSE}.
     * </p>
     */
    @Test
    void testOptionPaneDialogCreate() {
        OptionPaneCreator oCreator = new OptionPaneCreator();
        JOptionPane optionPane = oCreator.createOptionPane();
        JDialog dialog = oCreator.createDialog(optionPane);

        // Verifica se o JDialog não é nulo
        assertNotNull(dialog, "O JDialog não deve ser nulo.");
        // Verifica se o título do diálogo está correto
        assertEquals("Opções", dialog.getTitle(), "O título do diálogo deve ser 'Opções'.");
        // Verifica se a operação de fechamento do diálogo está correta
        assertEquals(WindowConstants.DISPOSE_ON_CLOSE, dialog.getDefaultCloseOperation(), "A operação de fechamento do diálogo deve ser DISPOSE_ON_CLOSE.");
    }
}
