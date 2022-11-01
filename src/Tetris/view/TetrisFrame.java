package Tetris.view;

import Tetris.controller.TetrisBoardController;

import javax.swing.*;

//Окно тетриса
public class TetrisFrame extends JFrame {
    JFrame f;
    public TetrisFrame() {
        f = new JFrame("Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(12 * 26 + 10, 26 * 23 + 25);
        f.setVisible(true);
        TetrisBoardController controller = new TetrisBoardController();
        controller.parent=this;
        f.add(controller.board);
        f.revalidate();
        f.addKeyListener(controller.setKeyListener());

    }

    public void close(){
        f.setVisible(false);
        f.dispose();
    }
}
