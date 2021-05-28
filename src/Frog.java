import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;

public class Frog extends GameObject
{

    private Board board;
    private Snake snake;
    private Vector2D position;
    private Color color = Color.magenta; // todo Zamienić na sprite
    private Vector2D direction = new Vector2D(0, 0);

    private final int TIMER_BASE_VALUE = 200;

    public Frog(Board board, Snake snake)
    {
        this.board = board;
        this.snake = snake;
    }

    public void MoveUp()
    {
        direction.set(0, -1);
    }

    public void MoveDown()
    {
        direction.set(0, 1);
    }

    public void MoveLeft()
    {
        direction.set(-1, 0);
    }

    public void MoveRight()
    {
        direction.set(1, 0);
    }

    @Override
    protected void awake()
    {
        Cell spawnCell = board.GetRandomEmptyCell();
        spawnCell.content = this;
        position = spawnCell.position.clone();
    }

    @Override
    protected void start()
    {
        timer = TIMER_BASE_VALUE;
    }

    @Override
    protected void update()
    {
        if (timerClockDown())
        {
            setTimer(TIMER_BASE_VALUE);

            int horizontalDistanceFromSnake = position.x - snake.Head().position.x;
            int verticalDistanceFromSnake = position.y - snake.Head().position.y;

            if (Math.abs(horizontalDistanceFromSnake) < Math.abs(verticalDistanceFromSnake))
            {
                if (verticalDistanceFromSnake < 0)
                    MoveUp();
                else
                    MoveDown();
            } 
            else
            {
                if (horizontalDistanceFromSnake < 0)
                    MoveLeft();
                else
                    MoveRight();
            }

            Vector2D newPosition = position.clone();
            newPosition.add(direction);

            Cell thisCell = board.GetCell(position);
            thisCell.content = null;

            if (board.GetCell(newPosition) instanceof Wall)
            {
                Cell emptyCell = board.GetClosestEmptyCell(thisCell);

                position.set(emptyCell.position);
                emptyCell.content = this;
            } else
            {
                Cell emptyCell = board.GetCell(newPosition);
                position.set(newPosition.clone());
                emptyCell.content = this;
            }

        }
    }

    @Override
    protected void render(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(position.x * Gameplay.CELL_WIDTH, position.y * Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH,
                Gameplay.CELL_HEIGHT);
    }
}
