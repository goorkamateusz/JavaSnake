import java.awt.Color;
import java.awt.Graphics2D;

public class Frog extends GameObject
{
    private Board board;
    private Vector2D position;
    private Color color = Color.magenta; // todo Zamienić na sprite
    private Vector2D direction = new Vector2D(0, 0);

    private final int TIMER_BASE_VALUE = 500;

    private Thread thread = null;
    private Pathfinding pathFinding;
    private Vector2D target;

    public Frog(Board board)
    {
        this.board = board;
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
        target = board.GetRandomEmptyCell().position.clone();
        pathFinding = new Pathfinding(position.clone(), target.clone(), board);
        thread = new Thread(pathFinding);
        thread.start();
    }

    @Override
    protected void update()
    {
        if (timerClockDown())
        {
            setTimer(TIMER_BASE_VALUE);

            try
            {
                thread.join();

                var path = pathFinding.Result;
                if (path.size() > 2)
                    direction = path.get(1).clone().getSubtracted(path.get(0).clone());

                board.GetCell(position).content = null;
                position.add(direction);
                board.GetCell(position).content = this;

                if (Vector2D.subtract(position, target).getLengthSqrt() < 3)
                    target = board.GetRandomEmptyCell().position.clone();
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }

            pathFinding = new Pathfinding(position.clone(), target.clone(), board);
            thread = new Thread(pathFinding);
            thread.start();
        }
    }

    @Override
    protected void onDestroy()
    {
        game.initialize(new Frog(board));
    }

    @Override
    protected void render(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(position.x * Gameplay.CELL_WIDTH, position.y * Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH,
                Gameplay.CELL_HEIGHT);
    }
}
