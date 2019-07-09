package com.joannas.swing;


import com.joannas.swing.conroller.Controler;
import com.joannas.swing.view.VocabularyGui;

public class Launcher {
    public static void main(String[] args) {
        VocabularyGui gui = new VocabularyGui();
        Controler controler = new Controler(gui);
        javax.swing.SwingUtilities.invokeLater(() -> gui.run(controler));
    }
}
