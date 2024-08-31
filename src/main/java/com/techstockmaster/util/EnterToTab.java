package com.techstockmaster.util;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/*
@see http://tips4java.wordpress.
@ descrpition :  Alterar a tecla ENTER para TAB em um componente
*/
public class EnterToTab {
    public static void add(Component comp) {
        Set<AWTKeyStroke> keystrokes = comp.getFocusTraversalKeys(0);
        Set<AWTKeyStroke> newKeystrokes = new HashSet<>(keystrokes);
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(10, 0));
        comp.setFocusTraversalKeys(0, newKeystrokes);
    }
}