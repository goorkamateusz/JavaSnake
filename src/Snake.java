import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends GameObject implements KeyListener {

    private Board board;
    private List<SnakePart> body;
    private int initialLength = 2;
    private Vector2D direction = new Vector2D(0, 1);

    private int points = 0; // todo licznik do punktów

    private final int TIMER_BASE_VALUE = 100;

    public Snake(Board board) {
        this.board = board;
    }

    public void MoveUp() {
        direction.set(0, -1);
    }

    public void MoveDown() {
        direction.set(0, 1);
    }

    public void ModeLeft() {
        direction.set(-1, 0);
    }

    public void MoveRight() {
        direction.set(1, 0);
    }

    private SnakePart AddPart() {
        int lastIndex = body.size() - 1;

        //todo Jeśli nie ma gdzie zespawnować to zrób coś
        Cell emptyCell = board.GetClosestEmptyCell(board.GetCell(body.get(lastIndex).position));

        SnakePart snakePart = new SnakePart(emptyCell.position);
        body.add(snakePart);

        emptyCell.content = snakePart;

        game.initialize(snakePart);
        return snakePart;
    }

    @Override
    protected void awake() {
        body = new ArrayList<SnakePart>();

        var emptyCell = board.GetEmptyCell();

        body.add(new SnakePart(emptyCell.position));
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
        setTimer(TIMER_BASE_VALUE);
    }

    @Override
    protected void update() {
        if (timerClockDown()) {
            setTimer(TIMER_BASE_VALUE);
            Vector2D lastPosition = new Vector2D(body.get(0).position);
            board.GetCell(lastPosition).content = null;

            SnakePart head = body.get(0);
            head.position.add(direction);
            Cell nextCell = board.GetCell(head.position);

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

            board.GetCell(head.position).content = head;

            for (int i = 1; i < body.size(); i++) {
                Vector2D tmp = new Vector2D(body.get(i).position);
                board.GetCell(tmp).content = null;

                SnakePart part = body.get(i);
                part.position.set(lastPosition);
                board.GetCell(part.position).content = part;

                lastPosition = tmp;
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
        if (e.getKeyCode() == KeyEvent.VK_W && direction.y != 1)
            MoveUp();
        else if (e.getKeyCode() == KeyEvent.VK_S && direction.y != -1)
            MoveDown();
        else if (e.getKeyCode() == KeyEvent.VK_D && direction.x != -1)
            MoveRight();
        else if (e.getKeyCode() == KeyEvent.VK_A && direction.x != 1)
            ModeLeft();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
