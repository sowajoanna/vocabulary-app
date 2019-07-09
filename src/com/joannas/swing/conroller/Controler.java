package com.joannas.swing.conroller;

import com.joannas.swing.conroller.exceptions.CantLoadDictionaryException;
import com.joannas.swing.conroller.exceptions.NoFileSelectedException;
import com.joannas.swing.model.Dictionary;
import com.joannas.swing.model.dao.CsvDAO;
import com.joannas.swing.model.dao.DAO;
import com.joannas.swing.view.VocabularyGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static com.joannas.swing.conroller.GameConstants.*;

public class Controler implements ActionListener {
    private VocabularyGui gui;
    private DAO dao;
    private Dictionary dictionary;
    private Integer currentPoints;
    private String wordToGuess;

    public Controler(VocabularyGui gui) {
        this.gui = gui;
        this.dao = new CsvDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case OPEN:
                createDictionaryFromFile();
                break;
            case START:
                handleStart();
                break;
            case CHECK:
                handleCheck();
                break;
        }
    }

    private void handleStart() {
        dictionary.fillItemsToDraw();
        gui.setScoreLabel(currentPoints = 0);
        gui.clearScoreInfo();
        gui.displayWord(dictionary.getRandomWord());
    }

    private void handleCheck() {
        String userAnswer = gui.getUserAnswer();
        boolean isCorrect = dictionary.isAnswerCorrect(userAnswer);
        gui.setScoreLabel(isCorrect ? ++currentPoints : --currentPoints);
        gui.updateScoreInfo(isCorrect);
        gui.clearTextArea();
        if (dictionary.isAnyLeft()) {
            gui.displayWord(dictionary.getRandomWord());
        } else {
            gui.showGameOverInfo(currentPoints);
        }
    }

    private void createDictionaryFromFile() {
        try {
            final File fileFromUser = gui.getFileFromUser();
            dictionary = dao.loadDictionary(fileFromUser);
            gui.showStart();
        } catch (NoFileSelectedException | CantLoadDictionaryException e) {
            System.err.println(e.getMessage());
        }
    }
}
