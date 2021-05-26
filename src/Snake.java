import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends GameObject implements KeyListener {

    private Board board;
    private List<SnakePart> body;

    private int initialLength = 2;

    private int horizontalSpeed = 0;
    private int verticalSpeed = 1;

    private int points = 0; // todo licznik do punktów

    private final int TIMMER_BASE_VALUE = 100;

    public Snake(Board board) {
        this.board = board;
    }

    public void MoveUp() {
        horizontalSpeed = 0;
        verticalSpeed = -1;
    }

    public void MoveDown() {
        horizontalSpeed = 0;
        verticalSpeed = 1;
    }

    public void ModeLeft() {
        horizontalSpeed = -1;
        verticalSpeed = 0;
    }

    public void MoveRight() {
        horizontalSpeed = 1;
        verticalSpeed = 0;
    }

    private SnakePart AddPart() {
        int lastIndex = body.size() - 1;

        //todo Jeśli nie ma gdzie zespawnować to zrób coś
        Cell emptyCell = board.GetClosestEmptyCell(board.GetCell(body.get(lastIndex).x, body.get(lastIndex).y));

        SnakePart snakePart = new SnakePart(emptyCell.x, emptyCell.y);
        body.add(snakePart);

        emptyCell.content = snakePart;

        game.initialize(snakePart);
        return snakePart;
    }

    @Override
    protected void awake() {
        body = new ArrayList<SnakePart>();

        var emptyCell = board.GetEmptyCell();

        body.add(new SnakePart(emptyCell.x, emptyCell.y));
        emptyCell.content = body.get(0);

        game.initialize(body.get(0));

        for (int i = 1; i < initialLength; i++) {
            AddPart();
        }
        game.addKeyListener(this);
    }

    @Override
    protected void start() {
        // todo Dopytać czemy jak się da tutaj to co jest w Awake to się wywala?
        setTimer(TIMMER_BASE_VALUE);
    }

    @Override
    protected void update() {
        if (timerClockDown()) {
            setTimer(TIMMER_BASE_VALUE);
            int[] lastPosition = new int[2];

            lastPosition[0] = body.get(0).x;
            lastPosition[1] = body.get(0).y;
            board.GetCell(lastPosition[0], lastPosition[1]).content = null;

            SnakePart head = body.get(0);
            head.x = head.x + horizontalSpeed;
            head.y = head.y + verticalSpeed;
            Cell nextCell = board.GetCell(head.x, head.y);

            // Jeśli ściana to popełnij seppuku
            if (nextCell instanceof Wall) {
                game.destroy(this);
            }

            // Jeśli snake part to popełnij seppuku

            // Jeśli owocek dodaj punkty i dostań ogon
            if (nextCell.content instanceof Fruit) {
                // Tutaj bym chciał zrobić casta ale nie umiem x.x
                // Fruit fruit = Fruit.cast(nextCell.content);

                AddPart();
                game.destroy(nextCell.content);
            }

            // Jeśli żaba to ją zjedz

            board.GetCell(head.x, head.y).content = head;

            for (int i = 1; i < body.size(); i++) {
                int[] tmp = new int[2];
                tmp[0] = body.get(i).x;
                tmp[1] = body.get(i).y;
                board.GetCell(tmp[0], tmp[1]).content = null;

                SnakePart part = body.get(i);
                part.x = lastPosition[0];
                part.y = lastPosition[1];
                board.GetCell(part.x, part.y).content = part;

                lastPosition[0] = tmp[0];
                lastPosition[1] = tmp[1];
            }
        }
    }

    @Override
    protected void onDestroy() {
        //todo usuwać każdy segment snake osobno ale nie umiem tego zgrać z game.destroy
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && verticalSpeed != 1)
            MoveUp();
        else if (e.getKeyCode() == KeyEvent.VK_S && verticalSpeed != -1)
            MoveDown();
        else if (e.getKeyCode() == KeyEvent.VK_D && horizontalSpeed != -1)
            MoveRight();
        else if (e.getKeyCode() == KeyEvent.VK_A && horizontalSpeed != 1)
            ModeLeft();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
