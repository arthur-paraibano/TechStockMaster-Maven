package com.techstockmaster.util;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnterToTabTeste {

    @Test
    void testAddEnterKeyToFocusTraversal() {
        // Cria um componente para testar
        JComponent component = new JButton();

        // Obtém o conjunto de teclas de navegação de foco antes de adicionar a tecla "Enter"
        Set<AWTKeyStroke> initialKeystrokes = component.getFocusTraversalKeys(0);

        // Adiciona a tecla "Enter" ao conjunto de teclas de navegação de foco
        EnterToTab.add(component);

        // Obtém o conjunto de teclas de navegação de foco após a adição
        Set<AWTKeyStroke> updatedKeystrokes = component.getFocusTraversalKeys(0);

        // Verifica se a tecla "Enter" foi adicionada
        AWTKeyStroke enterKeyStroke = AWTKeyStroke.getAWTKeyStroke(10, 0);
        assertTrue(updatedKeystrokes.contains(enterKeyStroke), "A tecla 'Enter' deve estar no conjunto de teclas de navegação de foco.");
    }
}