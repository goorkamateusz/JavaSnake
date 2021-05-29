import javax.swing.JLabel;

public class Gameplay extends GameBase {

    public static final int CELLS_X = 30;
    public static final int CELLS_Y = 30;
    public static final int CELL_HEIGHT = 10;
    public static final int CELL_WIDTH = 10;
    public static final int BOTTOM_TEXT_AREA = 30;
    public static final int HEIGHT_OF_WINDOW = CELL_WIDTH * CELLS_X + BOTTOM_TEXT_AREA;
    public static final int WIDTH_OF_WINDOW = CELL_HEIGHT * CELLS_Y;

    @Override
    protected void awakeScene() {
        openWindow(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW, "JavaSnake");

        // Na razie wszystko się tutaj inicjalizuje. Jakoś to się przeniesie
        Board board = new Board(10);


        Snake snake = new SnakeAI(board);
        initialize(snake);

        Snake player = new SnakePlayer(board);
        initialize(player);

        FruitGenerator fruitGenerator = new FruitGenerator(board, 10, 1);
        initialize(fruitGenerator);

        Frog frog = new Frog(board,snake);
        initialize(frog);
    }
}
