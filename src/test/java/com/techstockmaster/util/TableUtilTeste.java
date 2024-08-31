package com.techstockmaster.util;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testa a classe {@link TableUtil} para garantir que as operações de ocultar colunas e adicionar dicas de ferramentas
 * em uma tabela {@link JTable} funcionem conforme o esperado.
 */
public class TableUtilTeste {

    /**
     * Testa o método {@link TableUtil#hide(JTable, int)} para garantir que uma coluna é ocultada corretamente.
     * <p>
     * Verifica se a coluna especificada é de fato ocultada após a chamada do método, ajustando suas larguras
     * mínima, máxima, preferida e atual para zero.
     * </p>
     */
    @Test
    void testHideColumn() {
        // Configura uma tabela com duas colunas
        JTable table = new JTable(new DefaultTableModel(new Object[]{"Coluna 1", "Coluna 2"}, 0));

        // Oculta a coluna com índice 1
        TableUtil.hide(table, 1);

        // Obtém a coluna com índice 1
        TableColumn column = table.getColumnModel().getColumn(1);

        // Verifica se as larguras da coluna foram ajustadas para zero
        //assertEquals(0, column.getMaxWidth(), "A largura máxima da coluna deve ser 0.");
        assertEquals(0, column.getMinWidth(), "A largura mínima da coluna deve ser 0.");
        assertEquals(0, column.getPreferredWidth(), "A largura preferida da coluna deve ser 0.");
        assertEquals(0, column.getWidth(), "A largura da coluna deve ser 0.");
    }

    /**
     * Testa o método {@link TableUtil#showToltip(JTable, int)} para garantir que as dicas de ferramentas são configuradas
     * corretamente em uma coluna.
     * <p>
     * Verifica se a dica de ferramenta de cada célula na coluna especificada é definida como o valor da célula.
     * </p>
     */
    @Test
    void testShowTooltip() {
        // Configura uma tabela com uma coluna e uma célula com valor
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Coluna"}, 0);
        model.addRow(new Object[]{"Valor da célula"});
        JTable table = new JTable(model);

        // Adiciona uma dica de ferramenta à coluna com índice 0
        TableUtil.showToltip(table, 0);

        // Obtém o renderizador de células da coluna
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getColumnModel().getColumn(0).getCellRenderer();

        // Simula a renderização de uma célula com o valor "Valor da célula"
        Component component = renderer.getTableCellRendererComponent(table, "Valor da célula", false, false, 0, 0);

        // Verifica se o componente é uma instância de JLabel
        assertTrue(component instanceof JLabel, "O componente renderizado deve ser uma instância de JLabel.");

        // Verifica a dica de ferramenta do JLabel
        JLabel label = (JLabel) component;
        assertEquals("Valor da célula", label.getToolTipText(), "A dica de ferramenta deve ser 'Valor da célula'.");
    }
}