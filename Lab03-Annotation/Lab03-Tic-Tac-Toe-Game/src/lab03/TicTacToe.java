package lab03;

import lab03.helpers.enumerators.Difficulty;
import lab03.ticTacToeModules.AIControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Objects;

import static lab03.helpers.enumerators.Difficulty.*;

public class TicTacToe extends JPanel {
    private JButton[][] buttons;
    private boolean cross = true;//if this number is a even, then put a X. If it's odd, then put an O

    private int sizeX = 6;
    private int sizeY = 6;

    private int winSize = 3;

    private AIControl aiModule;

    private Difficulty difficulty = Easy;

    private TicTacToe() {
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(300, 200, 600, 700);
        window.setResizable(false);

        JPanel panelik = new JPanel();
        panelik.setLayout(new BoxLayout(panelik, BoxLayout.PAGE_AXIS));
        JPanel settingsPanel = new JPanel();


        JLabel size_xLabel = new JLabel("Size X");
        JLabel size_yLabel = new JLabel("Size Y");
        JLabel winLabel = new JLabel("Win count");
        JLabel classPath = new JLabel("null");

        JFileChooser fileChooser = new JFileChooser("H:\\Workspace\\Study\\programowanie-w-jezyku-java-tz\\Lab03-Annotation\\Lab03-Tic-Tac-Toe-AI\\out\\production\\Lab03-Tic-Tac-Toe-AI");
        fileChooser.setMultiSelectionEnabled(false);

        JFormattedTextField xSizeText = new JFormattedTextField(Integer.toString(sizeX));
        JFormattedTextField ySizeText = new JFormattedTextField(Integer.toString(sizeY));
        JFormattedTextField winSizeText = new JFormattedTextField(Integer.toString(winSize));

        xSizeText.setPreferredSize(new Dimension(75, 24));
        ySizeText.setPreferredSize(new Dimension(75, 24));
        winSizeText.setPreferredSize(new Dimension(75, 24));
        classPath.setPreferredSize(new Dimension(200, 24));

        JButton submitChanges = new JButton("Reset/Apply setting");
        JButton choseClass = new JButton("Chose class");
        JButton loadClass = new JButton("Load class");

        JComboBox<String> difficultyChoose = new JComboBox<>(new String[]{Easy.toString(), Medium.toString(), Hard.toString()});


        loadClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                aiModule.setClass(classPath.getText());
            }
        });

        choseClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean classLoaded = true;
                do {
                    int retVal = fileChooser.showOpenDialog(window);
                    if (retVal == JFileChooser.APPROVE_OPTION) {
                        File selectedfile = fileChooser.getSelectedFile();
                        String fileTmpPath = null;
                        try {
                            fileTmpPath = String.valueOf(selectedfile.toURI().toURL());
                        } catch (MalformedURLException e1) {
                            e1.printStackTrace();
                        }
                        classPath.setText(selectedfile.getName());
                        JOptionPane.showMessageDialog(window, classPath.getText());
                        classLoaded = aiModule.setClass(fileTmpPath, selectedfile.getName());
                    }
                } while (!classLoaded);

            }
        });

        difficultyChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (difficultyChoose.getSelectedItem() != null) {
                    if (Easy.toString().equals(difficultyChoose.getSelectedItem().toString())) {
                        difficulty = Easy;
                    }

                    if (Medium.toString().equals(difficultyChoose.getSelectedItem().toString())) {
                        difficulty = Medium;
                    }

                    if (Hard.toString().equals(difficultyChoose.getSelectedItem().toString())) {
                        difficulty = Hard;
                    }
                    JOptionPane.showMessageDialog(null, "Changed difficulty!");
                }
            }
        });

        submitChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionLogic();
            }

            private void actionLogic() {
                try {
                    sizeX = Integer.parseInt(xSizeText.getText());
                    sizeY = Integer.parseInt(ySizeText.getText());
                    winSize = Integer.parseInt(winSizeText.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Wrong input data");
                    xSizeText.setText(Integer.toString(sizeX));
                    ySizeText.setText(Integer.toString(sizeY));
                    winSizeText.setText(Integer.toString(winSize));
                    return;
                }

                xSizeText.setText(Integer.toString(sizeX));
                ySizeText.setText(Integer.toString(sizeY));
                winSizeText.setText(Integer.toString(winSize));

                setupTicTacToe();
            }
        });

        settingsPanel.add(size_xLabel);
        settingsPanel.add(xSizeText);
        settingsPanel.add(size_yLabel);
        settingsPanel.add(ySizeText);
        settingsPanel.add(winLabel);
        settingsPanel.add(winSizeText);
        settingsPanel.add(submitChanges);
        settingsPanel.add(choseClass);
        settingsPanel.add(classPath);
        settingsPanel.add(loadClass);
        settingsPanel.add(difficultyChoose);

        panelik.setMaximumSize(new Dimension(600, 700));
        settingsPanel.setMaximumSize(new Dimension(600, 100));
        this.setMaximumSize(new Dimension(600, 600));

        panelik.add(settingsPanel);
        panelik.add(this);

        window.getContentPane().add(panelik);

        aiModule = new AIControl();

        setupTicTacToe();
        window.setVisible(true);
        choseClass.doClick();
    }

    private void setupTicTacToe() {
        buttons = new JButton[sizeY][sizeX];
        setLayout(new GridLayout(sizeY, sizeX));
        initializebuttons();
    }

    private void initializebuttons() {
        removeAll();
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[i][j].addActionListener(new buttonListener());
                add(buttons[i][j]);
            }
        }
        revalidate();
        repaint();
    }

    private void resetButtons() {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    private class buttonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            JButton buttonClicked = (JButton) e.getSource();
            if (buttonClicked.getText().equals("")) {

                if (cross)
                    buttonClicked.setText("X");
                else
                    buttonClicked.setText("O");

                cross = !cross;
            }

            boolean reset = true;

            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    if (buttons[i][j].getText().equals("")) {
                        reset = false;
                    }
                }
            }

            if (reset) {
                JOptionPane.showMessageDialog(null, "Draw... Restarting!");
                resetButtons();
            }

            if (checkForWin()) {
                JOptionPane.showMessageDialog(null, "Game Over.");
                resetButtons();
            }

            aiMove();

            cross = !cross;

            if (checkForWin()) {
                JOptionPane.showMessageDialog(null, "Game Over.");
                resetButtons();
            }
        }

        private void aiMove() {
            String aiString;
            if (cross){
                aiString = "X";
            }else {
                aiString = "O";
            }

            aiModule.aiDoMove(buttons,sizeX,sizeY,aiString, difficulty);


        }

        boolean checkForWin() {
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {

                    if (buttons[i][j].getText().equals("")) {
                        continue;
                    }

                    int counter = 0;

                    if (i + winSize <= sizeY) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i + k][j].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }

                    if (counter == winSize - 1) {
                        return true;
                    } else {
                        counter = 0;
                    }

                    if (i - winSize + 1 >= 0 && j + winSize <= sizeX) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i - k][j + k].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }
                    if (counter == winSize - 1) {
                        return true;
                    } else {
                        counter = 0;
                    }

                    if (i + winSize <= sizeY && j + winSize <= sizeX) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i + k][j + k].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }
                    if (counter == winSize - 1) {
                        return true;
                    } else {
                        counter = 0;
                    }

                    if (j + winSize <= sizeX) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i][j + k].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }

                    if (counter == winSize - 1) {
                        return true;
                    }
                }
            }
            return false;
        }


    }


    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
}