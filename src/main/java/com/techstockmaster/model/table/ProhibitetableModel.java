package com.techstockmaster.model.table;

import com.techstockmaster.model.entities.Movement;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class ProhibitetableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final List<Movement> rows;
    private final String[] columns;

    public ProhibitetableModel(List<Movement> movement) {
        this.rows = movement;
        this.columns = new String[]{"Nome", "UN", "QUANTIDADE"};
    }

    // Retorna a quantidade de linhas
    @Override
    public int getRowCount() {
        return this.rows.size();
    }

    // Retorna a quantidade de colunas
    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Movement obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getEquipment().getNome();
            case 1:
                return obj.getEquipment().getAbreviacao_un();
            case 2:
                return obj.getAmount();
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}