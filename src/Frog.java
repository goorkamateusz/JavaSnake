import java.awt.Color;
import java.awt.Graphics2D;
public class Frog extends GameObject {

    private Board board;
    private Vector2D position;
    private Color color = Color.magenta; // todo Zamienić na sprite
    private Vector2D direction = new Vector2D(0, 0);

    private final int timerBaseValue = 100;

    public Frog(Board board)
    {
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

    @Override
    protected void awake() {
        Cell spawnCell = board.GetRandomEmptyCell();
        spawnCell.content = this;
        position = spawnCell.position;
    }

    @Override
    protected void start() {
        timer = timerBaseValue;
    }

    @Override
    protected void update() {
        if (timerClockDown()) {
            setTimer(timerBaseValue);
            
            //todo AI uciekania żaby
        }
    }

    @Override
    protected void render(Graphics2D g) 
    {
        g.setColor(color);
        g.fillRect(position.x*Gameplay.CELL_WIDTH, position.y*Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH, Gameplay.CELL_HEIGHT);
    }
}
