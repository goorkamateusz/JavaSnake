import java.awt.Color;

public class Gameplay extends GameBase {

    public static final int CELLS_X = 10;
    public static final int CELLS_Y = 10;
    public static final int CELL_HEIGHT = 30;
    public static final int CELL_WIDTH = 30;
    public static final int HEIGHT_OF_WINDOW = CELL_WIDTH * CELLS_X;
    public static final int WIDTH_OF_WINDOW = CELL_HEIGHT * CELLS_Y;

    @Override
    protected void awakeScene() {
        openWindow(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW, "JavaSnake");
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        initialize(new TestSqrt());
        initialize(new Bonus());
        initialize(new Bonus());
        initialize(new Bonus(Color.BLACK));
        initialize(new Bonus(Color.GREEN));
    }
}
