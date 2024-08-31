package com.techstockmaster.model.table;

import com.techstockmaster.model.entities.CalledTI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class CalledTITableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final List<CalledTI> rows;
    private final String[] columns;

    public CalledTITableModel(List<CalledTI> calledTIs) {
        this.rows = calledTIs;
        this.columns = new String[]{"ID", "TECNICO", "SETOR", "DESCRICAO", "DATA"};
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
        CalledTI obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getTecnico().getNomeLogin();
            case 2:
                return obj.getSetor().getnome();
            case 3:
                return obj.getDescricao();
            case 4:
                Date date = obj.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(date);
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}