package com.techstockmaster.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Utilitário para manipulação de tabelas {@link JTable} em Swing.
 * <p>
 * Esta classe fornece métodos estáticos para ocultar colunas e adicionar dicas de ferramentas (tooltips)
 * às células de uma tabela.
 * </p>
 */
public class TableUtil {

    /**
     * Oculta uma coluna em uma tabela {@link JTable}.
     * <p>
     * Este método ajusta as larguras mínima, máxima, preferida e atual da coluna para zero, efetivamente
     * ocultando-a da visualização da tabela.
     * </p>
     *
     * @param table      a tabela {@link JTable} na qual a coluna deve ser oculta
     * @param indexColum o índice da coluna a ser ocultada
     * @throws IndexOutOfBoundsException se o índice da coluna estiver fora dos limites da tabela
     */
    public static void hide(JTable table, int indexColum) {
        // tem que ser nessa sequência para ocultar a coluna
        table.getColumnModel().getColumn(indexColum).setMaxWidth(0);
        table.getColumnModel().getColumn(indexColum).setMinWidth(0);
        table.getColumnModel().getColumn(indexColum).setPreferredWidth(0);
        table.getColumnModel().getColumn(indexColum).setWidth(0);
    }

    /**
     * Adiciona uma dica de ferramenta (tooltip) a uma coluna em uma tabela {@link JTable}.
     * <p>
     * Este método define uma renderização personalizada para a coluna especificada, de modo que a dica
     * de ferramenta de cada célula seja configurada para o valor da célula. Se o valor da célula for nulo,
     * a dica de ferramenta será uma string vazia.
     * </p>
     *
     * @param table      a tabela {@link JTable} na qual a coluna deve ter dicas de ferramentas
     * @param indexColum o índice da coluna a ser configurada com dicas de ferramentas
     * @throws IndexOutOfBoundsException se o índice da coluna estiver fora dos limites da tabela
     */
    public static void showToltip(JTable table, int indexColum) {
        table.getColumnModel().getColumn(indexColum).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setToolTipText(value == null ? " " : value.toString()); // Define a dica de ferramenta como o valor da célula
                }
                return c;
            }
        });
    }
}