package Tetris.controller;

import Tetris.model.Tetramino;
import Tetris.model.TetraminoType;
import Tetris.view.TetrisBoard;
import Tetris.view.TetrisFrame;
import Tetris.view.TetrisGameOverFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class TetrisBoardController {

    private Tetramino[] tetraminos= {
            new Tetramino(TetraminoType.I), new Tetramino(TetraminoType.J),
            new Tetramino(TetraminoType.L), new Tetramino(TetraminoType.O),
            new Tetramino(TetraminoType.S), new Tetramino(TetraminoType.T),
            new Tetramino(TetraminoType.Z)
    };
    private Point pieceOrigin;
    private boolean gameOver=false;
    public TetrisFrame parent;
    public TetrisBoard board=new TetrisBoard();
    private int currentPiece;
    private int rotation;
    public int score=0;
    private ArrayList<Integer> nextPieces = new ArrayList<Integer>();
    private final Color[] tetraminoColors = {
            Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
    };

    public TetrisBoardController(){
        board.init();
        board.controller=this;
        newPiece();
        //Цикл падения тетрамино каждые полсекунды
        new Thread() {
            @Override public void run() {
                while (!gameOver) {
                    try {

                        board.repaint();
                        Thread.sleep(500);
                        dropDown();
                    } catch ( InterruptedException e ) {}
                }
            }
        }.start();
    }

    // Добавление нового падающего тетрамино
    public void newPiece() {
        pieceOrigin = new Point(5, 2);
        rotation = 0;
        if (nextPieces.isEmpty()) {
            Collections.addAll(nextPieces, 0, 1, 2, 3, 4, 5, 6);
            Collections.shuffle(nextPieces);
        }
        currentPiece = nextPieces.get(0);
        nextPieces.remove(0);
    }

    // Проверка колизии тетрамино
    private boolean collidesAt(int x, int y, int rotation) {
        for (Point p : tetraminos[currentPiece].coords[rotation]) {
            if (board.table[p.x + x][p.y + y] != Color.DARK_GRAY) {
                return true;
            }
        }
        return false;
    }

    // Поворот тетрамино
    public void rotate(int i) {
        int newRotation = (rotation + i) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y, newRotation)) {
            rotation = newRotation;
        }
    }

    // Передвинуть тетрамино вправо-влево
    public void move(int i) {
        if (!collidesAt(pieceOrigin.x + i, pieceOrigin.y, rotation)) {
            pieceOrigin.x += i;
        }
    }


    // Опускает тетрамино
    public void dropDown() {
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y + 1, rotation)) {
            pieceOrigin.y += 1;
        } else {
            fixToBoard();
        }
    }




    // Делаем упавшую тетрамино частью доски для проверки колизии
    public void fixToBoard() {
        for (Point p : tetraminos[currentPiece].coords[rotation]) {
            board.table[pieceOrigin.x + p.x][pieceOrigin.y + p.y] = tetraminoColors[currentPiece];
        }
        if (!gameOver) checkFinish();
        clearRows();
        newPiece();
    }


    // Очистка заполненных рядов
    public void clearRows() {
        boolean gap;
        int numClears = 0;

        for (int j = 21; j > 0; j--) {
            gap = false;
            for (int i = 1; i < 11; i++) {
                if (board.table[i][j] == Color.DARK_GRAY) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                for (int k = j-1; k > 0; k--) {
                    for (int i = 1; i < 11; i++) {
                        board.table[i][k+1] = board.table[i][k];
                    }
                }
                j += 1;
                numClears += 1;
            }
        }

        switch (numClears) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
        }
    }



    // Отрисовать падающую тетромино
    public void drawPiece(Graphics g) {
        g.setColor(tetraminoColors[currentPiece]);
        for (Point p : tetraminos[currentPiece].coords[rotation]) {
            g.fillRect((p.x + pieceOrigin.x) * 26,
                    (p.y + pieceOrigin.y) * 26,
                    25, 25);
        }
    }

    //Проверка на окончание игры
    private void checkFinish(){
        for (int i=0;i<board.table.length;i++) if (board.table[i][4]!=Color.LIGHT_GRAY && board.table[i][4]!=Color.DARK_GRAY) gameOver=true;
        if (gameOver){
            new TetrisGameOverFrame(score);
            parent.close();
        }
    }

    //Обработка нажатия кнопок
    public KeyListener setKeyListener(){
        return new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        rotate(-1);
                        board.repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        rotate(+1);
                        board.repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        move(-1);
                        board.repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        move(+1);
                        board.repaint();
                        break;
                    case KeyEvent.VK_SPACE:
                        dropDown();
                        board.repaint();
                        score += 1;
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        };
    }

}
