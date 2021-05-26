import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends GameObject implements KeyListener {
    public int horizontalSpeed = 0;
    public int verticalSpeed = +1;

    private int initialLength = 3;
    private Board board;
    public ArrayList<SnakePart> body; // to będzie private

    public Snake(Board board) {
        this.board = board;
    }

    @Override
    protected void awake() {
        body = new ArrayList<SnakePart>();
        var emptyCell = board.GetEmptyCell();
        body.add(new SnakePart(emptyCell.x, emptyCell.y));
        emptyCell.content = body.get(0);
        // Gameplay.initialize(body.get(0));
        for (int i = 1; i < initialLength; i++) {
            emptyCell = board.GetClosestEmptyCell(board.GetCell(body.get(i - 1).x, body.get(i - 1).y));
            body.add(new SnakePart(emptyCell.x, emptyCell.y));
            emptyCell.content = body.get(i);
            // Gameplay.initialize(body.get(i));
        }

        game.addKeyListener(this);
    }

    @Override
    protected void start() {
        timer = 500;
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("naciśnięto " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("puszczono " + e.getKeyCode());
    }
}
