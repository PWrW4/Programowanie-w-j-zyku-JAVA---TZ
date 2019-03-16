import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JPanel
{
    private JButton[][] buttons;
    private int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O

    private int sizeX=6;
    private int sizeY=6;

    private int winSize=2;

    private TicTacToe()
    {
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(300,200,600,800);
        window.setResizable(false);

        JPanel panelik = new JPanel();
        panelik.setLayout(new BoxLayout(panelik,BoxLayout.PAGE_AXIS));
        JPanel settingsPanel = new JPanel();

        panelik.setMaximumSize(new Dimension(600,800));
        settingsPanel.setMaximumSize(new Dimension(600,200));
        this.setMaximumSize(new Dimension(600,600));

        panelik.add(settingsPanel);
        panelik.add(this);

        window.getContentPane().add(panelik);

        setupTicTacToe();
        window.setVisible(true);
    }

    private void setupTicTacToe()
    {
        buttons = new JButton[sizeY][sizeX];
        setLayout(new GridLayout(sizeY,sizeX));
        initializebuttons();
    }

    private void initializebuttons()
    {
        removeAll();
        for(int i = 0; i < sizeY; i++)
        {
            for (int j = 0; j < sizeX; j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[i][j].addActionListener(new buttonListener());
                add(buttons[i][j]); //adds this button to JPanel (note: no need for JPanel.add(...)
                //because this whole class is a JPanel already
            }
        }
    }
    private void resetButtons()
    {
        for(int i = 0; i < sizeY; i++)
        {
            for (int j = 0; j < sizeX; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    private class buttonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            JButton buttonClicked = (JButton)e.getSource();
            if (buttonClicked.getText().equals("")){

                if(alternate%2 == 0)
                    buttonClicked.setText("X");
                else
                    buttonClicked.setText("O");

                alternate++;
            }


            if(checkForWin())
            {
                JOptionPane.showMessageDialog(null, "Game Over.");
                resetButtons();
            }
        }

        boolean checkForWin()
        {
            for (int i = 0; i < sizeY; i++)
            {
                for (int j = 0; j < sizeX; j++)
                {

                    if (buttons[i][j].getText().equals("")){
                        continue;
                    }

                    int counter = 0;

                    if (i+winSize<=sizeY) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i + k][j].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }

                    if (counter == winSize-1){
                        return true;
                    }else {
                        counter = 0;
                    }

                    if (i-winSize+1>=0 && j+winSize<=sizeX) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i - k][j + k].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }
                    if (counter == winSize-1){
                        return true;
                    }else {
                        counter = 0;
                    }

                    if (i+winSize<=sizeY && j+winSize<=sizeX) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i + k][j + k].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }
                    if (counter == winSize-1){
                        return true;
                    }else {
                        counter = 0;
                    }

                    if (j+winSize<=sizeX) {
                        for (int k = 1; k < winSize; k++) {
                            if (!buttons[i][j].getText().equals(buttons[i][j + k].getText())) {
                                break;
                            }
                            counter++;
                        }
                    }

                    if (counter == winSize-1){
                        return true;
                    }
                }
            }
            return false;
        }


    }

    public static void main(String[] args)
    {
        TicTacToe game = new TicTacToe();
    }
}