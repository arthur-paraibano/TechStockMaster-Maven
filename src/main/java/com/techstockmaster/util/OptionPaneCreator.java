package com.techstockmaster.util;

import javax.swing.*;
import java.util.Objects;

public class OptionPaneCreator {

    public JOptionPane createOptionPane() {
        JOptionPane optionPane = new JOptionPane("Tem certeza que deseja Salvar?", JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION, null, new Object[]{}, null);
        Icon figura = new ImageIcon(Objects.requireNonNull(getClass().getResource("/com/techstockmaster/resources/pensando.png")));
        optionPane.setIcon(figura);
        return optionPane;
    }

    public JDialog createDialog(JOptionPane optionPane) {
        JDialog dialog = optionPane.createDialog("Opções");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        return dialog;
    }
}