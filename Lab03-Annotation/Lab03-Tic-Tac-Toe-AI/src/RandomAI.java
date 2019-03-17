import lab03.helpers.annotations.DifficultyLevel;
import lab03.helpers.annotations.TicTacToeAI;
import lab03.helpers.enumerators.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

@TicTacToeAI
public class RandomAI {

    public RandomAI() {
        System.out.println("Initialized: " + this.getClass().getName().toString());
    }

    @DifficultyLevel(level = Difficulty.Easy)
    public void totalRandom(JButton[][] buttons, int sizeX, int sizeY, String charToPut) {

        System.out.println("Calling in: " + this.getClass().getName().toString() + " easy method");

        Random generator = new Random();
        int x = generator.nextInt(sizeX);
        int y = generator.nextInt(sizeY);
        do {
            x = generator.nextInt(sizeX);
            y = generator.nextInt(sizeY);
        } while (!buttons[x][y].getText().equals(""));

        buttons[x][y].setText(charToPut);
    }

    @DifficultyLevel(level = Difficulty.Medium)
    public void randomNextToOther(JButton[][] buttons, int sizeX, int sizeY, String charToPut) {
        System.out.println("Calling in: " + this.getClass().getName().toString() + " medium method");

        ArrayList<Point> selectedButtons = new ArrayList<Point>();

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (!buttons[i][j].getText().equals("")) {
                    selectedButtons.add(new Point(i, j));
                }
            }
        }

        if (selectedButtons.size() == 0) {
            totalRandom(buttons, sizeX, sizeY, charToPut);
        }

        Random generator = new Random();
        int index = generator.nextInt(selectedButtons.size());

        Point selected = selectedButtons.get(index);

        selectedButtons.clear();

        if (selected.x + 1 < sizeX && selected.y + 1 < sizeY) {
            if (buttons[selected.x+1][selected.y+1].getText().equals("")){
                selectedButtons.add(new Point(selected.x + 1, selected.y + 1));
            }
        }

        if (selected.x + 1 < sizeX) {
            if (buttons[selected.x+1][selected.y].getText().equals("")) {
                selectedButtons.add(new Point(selected.x + 1, selected.y));
            }
        }

        if (selected.x + 1 < sizeX && selected.y - 1 >= 0) {
            if (buttons[selected.x+1][selected.y-1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x + 1, selected.y - 1));
            }
        }

        if (selected.y - 1 >= 0) {
            if (buttons[selected.x][selected.y-1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x, selected.y - 1));
            }
        }

        if (selected.y + 1 < sizeY) {
            if (buttons[selected.x][selected.y+1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x, selected.y + 1));
            }
        }

        if (selected.x - 1 >= 0 && selected.y + 1 < sizeY) {
            if (buttons[selected.x-1][selected.y+1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x - 1, selected.y + 1));
            }
        }

        if (selected.x - 1 >= 0) {
            if (buttons[selected.x-1][selected.y].getText().equals("")) {
                selectedButtons.add(new Point(selected.x - 1, selected.y));
            }
        }

        if (selected.x - 1 >= 0 && selected.y - 1 >= 0) {
            if (buttons[selected.x-1][selected.y-1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x - 1, selected.y - 1));
            }
        }

        if (selectedButtons.size() == 0) {
            totalRandom(buttons, sizeX, sizeY, charToPut);
        }

        index = generator.nextInt(selectedButtons.size());

        selected = selectedButtons.get(index);

        buttons[selected.x][selected.y].setText(charToPut);

    }

    @DifficultyLevel(level = Difficulty.Hard)
    public void randomNextToMy(JButton[][] buttons, int sizeX, int sizeY, String charToPut) {
        System.out.println("Calling in: " + this.getClass().getName().toString() + " hard method");
        ArrayList<Point> selectedButtons = new ArrayList<Point>();

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (!buttons[i][j].getText().equals(charToPut)) {
                    selectedButtons.add(new Point(i, j));
                }
            }
        }

        if (selectedButtons.size() == 0) {
            totalRandom(buttons, sizeX, sizeY, charToPut);
        }

        Random generator = new Random();
        int index = generator.nextInt(selectedButtons.size());

        Point selected = selectedButtons.get(index);

        selectedButtons.clear();

        if (selected.x + 1 < sizeX && selected.y + 1 < sizeY) {
            if (buttons[selected.x+1][selected.y+1].getText().equals("")){
                selectedButtons.add(new Point(selected.x + 1, selected.y + 1));
            }
        }

        if (selected.x + 1 < sizeX) {
            if (buttons[selected.x+1][selected.y].getText().equals("")) {
                selectedButtons.add(new Point(selected.x + 1, selected.y));
            }
        }

        if (selected.x + 1 < sizeX && selected.y - 1 >= 0) {
            if (buttons[selected.x+1][selected.y-1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x + 1, selected.y - 1));
            }
        }

        if (selected.y - 1 >= 0) {
            if (buttons[selected.x][selected.y-1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x, selected.y - 1));
            }
        }

        if (selected.y + 1 < sizeY) {
            if (buttons[selected.x][selected.y+1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x, selected.y + 1));
            }
        }

        if (selected.x - 1 >= 0 && selected.y + 1 < sizeY) {
            if (buttons[selected.x-1][selected.y+1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x - 1, selected.y + 1));
            }
        }

        if (selected.x - 1 >= 0) {
            if (buttons[selected.x-1][selected.y].getText().equals("")) {
                selectedButtons.add(new Point(selected.x - 1, selected.y));
            }
        }

        if (selected.x - 1 >= 0 && selected.y - 1 >= 0) {
            if (buttons[selected.x-1][selected.y-1].getText().equals("")) {
                selectedButtons.add(new Point(selected.x - 1, selected.y - 1));
            }
        }

        if (selectedButtons.size() == 0) {
            totalRandom(buttons, sizeX, sizeY, charToPut);
        }

        index = generator.nextInt(selectedButtons.size());

        selected = selectedButtons.get(index);

        buttons[selected.x][selected.y].setText(charToPut);
    }


}
