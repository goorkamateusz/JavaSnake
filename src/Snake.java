import java.awt.Color;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends GameObject implements KeyListener {

    private Board board;
    private ArrayList<SnakePart> body;

    private int initialLength = 2;

    private int horizontalSpeed = 0;
    private int verticalSpeed = 1;

    private int points = 0; // todo licznik do punktów

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

        Cell emptyCell = board.GetClosestEmptyCell(board.GetCell(body.get(lastIndex).x, body.get(lastIndex).y)); // todo
                                                                                                                 // Jeśli
                                                                                                                 // null
                                                                                                                 // to
                                                                                                                 // nie
                                                                                                                 // ma
                                                                                                                 // gdzie
                                                                                                                 // zespawnować
                                                                                                                 // i
                                                                                                                 // coś
                                                                                                                 // zrób

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
        setTimer(500);
    }

    @Override
    protected void update() {
        if (timerClockDown()) {
            setTimer(500);
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

            // Jeśli owocek dodaj punkty i dostań ogon
            if (nextCell.content instanceof Fruit) {
                // Fruit fruit = Fruit.cast(nextCell.content); Tutaj bym chciał zrobić casta ale
                // nie umiem x.x
                AddPart();
                game.destroy(nextCell.content);
            }

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
        // todo usuwanie każdej pojedyńczej części wensza
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("naciśnięto " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            MoveUp();
        else if (e.getKeyCode() == KeyEvent.VK_S)
            MoveDown();
        else if (e.getKeyCode() == KeyEvent.VK_D)
            MoveRight();
        else if (e.getKeyCode() == KeyEvent.VK_A)
            ModeLeft();
    }
}
