package Tetris.view;

import Tetris.controller.TetrisBoardController;

import javax.swing.*;
import java.awt.*;

public class TetrisBoard extends JPanel {

    public Color[][] table;
    public TetrisBoardController controller;

    //Инициализация поля
    public void init() {
        table = new Color[12][24];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                if (i == 0 || i == 11 || j == 22) {
                    table[i][j] = Color.LIGHT_GRAY;
                } else {
                    table[i][j] = Color.DARK_GRAY;
                }
            }
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {
        // Отрисовка поля
        g.fillRect(0, 0, 26*12, 26*23);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                g.setColor(table[i][j]);
                g.fillRect(26*i, 26*j, 25, 25);
            }
        }

        // Отображение счёта
        g.setColor(Color.WHITE);
        g.drawString("" + controller.score, 19*12, 25);

        // Отрисовка падающего тетрамино
        controller.drawPiece(g);
    }


}
