package com.joannas.swing.view;

import com.joannas.swing.conroller.exceptions.NoFileSelectedException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

import static com.joannas.swing.conroller.GameConstants.*;

public class VocabularyGui {
    private JFrame window;
    private JMenuBar menuBar;
    private JMenu files;
    private JMenuItem open;
    private JLabel score;
    private JLabel userScore;
    private JLabel plusOne;
    private JLabel minusOne;
    private JLabel englishWord;
    private JLabel appAuthor;
    private JTextField textField;
    private JButton check;
    private JLabel image;
    private JFileChooser fileChooser;
    private JButton start;

    public void run(ActionListener actionListener) {
        createAndShowGui();
        setController(actionListener);
    }

    private void createAndShowGui() {
        window = new JFrame("Vocabulary Game");
        window.setLayout(null);
        window.setMinimumSize(new Dimension(500, 500));
        window.setResizable(false);
        window.getContentPane().setBackground(new Color(0, 153, 153));

        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(500, 30));
        menuBar.setBackground(new Color(51, 204, 204));
        menuBar.setBorderPainted(false);
        window.setJMenuBar(menuBar);

        files = new JMenu("Files");
        files.setForeground(Color.WHITE);
        menuBar.add(files);

        open = new JMenuItem("Open file");
        open.setBackground(new Color(51, 204, 204));
        open.setForeground(Color.WHITE);
        open.setActionCommand(OPEN);
        files.add(open);

        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.csv", "csv"));

        start = new JButton("Start");
        start.setBounds(50, 10, 100, 20 );
        start.setFont(new Font("Arial", Font.BOLD, 20));
        start.setBackground(new Color(255, 199, 103));
        start.setForeground(Color.BLACK);
        start.setActionCommand(START);

        score = new JLabel("Score:");
        score.setFont(new Font("Serif", Font.ITALIC, 24));
        score.setBounds(300, 3, 75, 24);
        window.getContentPane().add(score);

        userScore = new JLabel("100");
        userScore.setFont(new Font("Serif", Font.ITALIC, 24));
        userScore.setBounds(375, 3, 50, 24);
        window.getContentPane().add(userScore);

        plusOne = new JLabel("+1");
        plusOne.setFont(new Font("Serif", Font.PLAIN, 10));
        plusOne.setBounds(435, 0, 15, 10);
        window.getContentPane().add(plusOne);

        minusOne = new JLabel("-1");
        minusOne.setFont(new Font("Serif", Font.PLAIN, 10));
        minusOne.setBounds(437, 15, 15, 10);
        window.getContentPane().add(minusOne);

        englishWord = new JLabel("English Word");
        englishWord.setBounds(125, 50, 300, 40);
        englishWord.setForeground(Color.BLACK);
        englishWord.setFont(new Font("Arial", Font.BOLD, 36));
        window.getContentPane().add(englishWord);

        appAuthor = new JLabel("Author: JS");
        appAuthor.setBounds(410, 410, 100, 12);
        appAuthor.setForeground(Color.BLACK);
        appAuthor.setFont(new Font("Arial", Font.PLAIN, 12));
        window.getContentPane().add(appAuthor);

        textField = new JTextField();
        textField.setBounds(125, 100, 225, 40);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        window.getContentPane().add(textField);

        check = new JButton("Check");
        check.setBounds(165, 150, 150, 30 );
        check.setFont(new Font("Arial", Font.BOLD, 24));
        check.setBackground(new Color(255, 199, 103));
        check.setForeground(Color.BLACK);
        check.setActionCommand(CHECK);
        window.getContentPane().add(check);

        image = new JLabel(new ImageIcon("./images/queen-elizabeth2.jpg"));
        image.setBounds(0, 200, 500, 250);
        window.getContentPane().add(image);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void showStart() {
        window.getContentPane().add(start);
        window.repaint();
    }

    public void setController(ActionListener actionListener) {
        open.addActionListener(actionListener);
        check.addActionListener(actionListener);
        start.addActionListener(actionListener);
    }

    public File getFileFromUser() throws NoFileSelectedException {
        fileChooser.showOpenDialog(window);
        final File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null) {
            return selectedFile;
        }
        throw new NoFileSelectedException("No selected file.");
    }

    public void setScoreLabel(Integer currentPoints) {
        userScore.setText(currentPoints.toString());
    }

    public void clearScoreInfo() {
        plusOne.setVisible(false);
        minusOne.setVisible(false);
    }

    public void displayWord(String wordToGuess) {
        englishWord.setText(wordToGuess);
    }

    public String getUserAnswer() {
        return this.textField.getText();
    }

    public void updateScoreInfo(boolean isCorrect) {
        plusOne.setVisible(isCorrect);
        minusOne.setVisible(!isCorrect);
    }

    public void clearTextArea() {
        textField.setText("");
    }

    public void showGameOverInfo(Integer currentPoints) {
        JOptionPane.showMessageDialog(window, "You have finished word game!\nFinal score: " + currentPoints, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        clearWindow();
    }

    private void clearWindow() {
        userScore.setText("0");
        clearScoreInfo();
        englishWord.setText("English Word");
    }

}
