package Tetris.model;
import java.awt.Point;

public class Tetramino {
    public Point[][] coords;

    public Tetramino(TetraminoType type){
        switch(type){
            case I:
                coords=new Point[][]{
                        { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
                        { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
                        { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
                        { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }
                };
                break;

            case J:
                coords=new Point[][]{
                        { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) },
                        { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
                        { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) },
                        { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) }
                };
                break;

            case L:
                coords=new Point[][]{
                        { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
                        { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) },
                        { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
                        { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) }
                };
                break;

            case O:
                coords=new Point[][]{
                        { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
                        { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
                        { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
                        { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }
                };
                break;

            case S:
                coords=new Point[][]{
                        { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
                        { new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
                        { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
                        { new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }
                };

            case T:
                coords=new Point[][]{
                        { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
                        { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
                        { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
                        { new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) }
                };
                break;

            case Z:
                coords=new Point[][]{
                        { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
                        { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) },
                        { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
                        { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }
                };
                break;
        }
    }
}

