import lab03.helpers.annotations.DifficultyLevel;
import lab03.helpers.annotations.TicTacToeAI;
import lab03.helpers.enumerators.Difficulty;

import javax.swing.*;
import java.util.Random;

@TicTacToeAI
public class RandomAI {


    @DifficultyLevel(level = Difficulty.Easy)
    public void totalRandom(JButton[][] buttons, int sizeX, int sizeY, String charToPut){

        Random generator = new Random();
        int x = generator.nextInt(sizeX);
        int y = generator.nextInt(sizeY);
        do {
            x = generator.nextInt(sizeX);
            y = generator.nextInt(sizeY);
        }while (!buttons[x][y].getText().equals(""));

        buttons[x][y].setText(charToPut);
    }

    @DifficultyLevel(level = Difficulty.Medium)
    public void randomNextToOther(JButton[][] buttons, int sizeX, int sizeY, String charToPut){
        System.out.println("medium");
        totalRandom(buttons,sizeX,sizeY,charToPut);
    }

    @DifficultyLevel(level = Difficulty.Hard)
    public void randomNextToMy(JButton[][] buttons, int sizeX, int sizeY, String charToPut){
        System.out.println("hard");
        totalRandom(buttons,sizeX,sizeY,charToPut);
    }


}
