public class Gameplay extends GameBase {

    public static final int CELLS_X = 30;
    public static final int CELLS_Y = 30;
    public static final int CELL_HEIGHT = 10;
    public static final int CELL_WIDTH = 10;
    public static final int HEIGHT_OF_WINDOW = CELL_WIDTH * CELLS_X;
    public static final int WIDTH_OF_WINDOW = CELL_HEIGHT * CELLS_Y;

    @Override
    protected void awakeScene() {
        openWindow(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW, "JavaSnake");

        // Na razie wszystko się tutaj inicjalizuje. Jakoś to się przeniesie
        Board board = new Board();
        for (Cell[] cells : board.board) {
            for (Cell cell : cells) {
                initialize(cell);
            }
        }

        Snake snake = new Snake(board);
        initialize(snake);
        for (int i = 0; i < snake.body.size(); i++) {
            initialize(snake.body.get(i));
        }
    }
}
