public class Gameplay extends GameBase
{

    public static final int CELLS_X = 40;
    public static final int CELLS_Y = 40;

    public static final int CELL_HEIGHT = 10;
    public static final int CELL_WIDTH = 10;

    public static final int BOTTOM_TEXT_AREA = 30;
    public static final int HEIGHT_OF_WINDOW = CELL_WIDTH * CELLS_X + BOTTOM_TEXT_AREA;
    public static final int WIDTH_OF_WINDOW = CELL_HEIGHT * CELLS_Y;

    @Override
    protected void awakeScene()
    {
        openWindow(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW, "JavaSnake");

        Board board = new Board(10);

        Snake snake = new SnakeAI(board);
        initialize(snake);

        initialize(new SnakeAI(board));
        initialize(new SnakeAI(board));
        initialize(new SnakeAI(board));
        initialize(new SnakeAI(board));

        Snake player = new SnakePlayer(board);
        initialize(player);

        FruitGenerator fruitGenerator = new FruitGenerator(board, 20, 1);
        initialize(fruitGenerator);

        initialize(new Frog(board));
        initialize(new Frog(board));
        initialize(new Frog(board));
        initialize(new Frog(board));
    }
}
