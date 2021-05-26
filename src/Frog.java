import java.awt.Color;
import java.awt.Graphics2D;
public class Frog extends GameObject {

    private Board board;
    private int x;
    private int y;

    private Color color= Color.magenta; // todo Zamienić na sprite

    private int horizontalSpeed = 0;
    private int verticalSpeed = 0;

    private final int timerBaseValue = 100;

    public Frog(Board board)
    {   
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

    @Override
    protected void awake() {
        Cell spawnCell = board.GetRandomEptyCell();
        spawnCell.content = this;
        x = spawnCell.x;
        y = spawnCell.y;
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
        g.fillRect(x*Gameplay.CELL_WIDTH, y*Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH, Gameplay.CELL_HEIGHT);
    }
}
