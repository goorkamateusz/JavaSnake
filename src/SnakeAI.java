import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

    /**
     * Class of snake AI.
     */
public class SnakeAI extends Snake
{
    static private int AICounter;
    private int aiId;
    private Thread thread = null;
    private Pathfinding pathFinding;

    /**
     * Constructor that pass reference to board.
     */
    public SnakeAI(Board board)
    {
        super(board);
        aiId = AICounter % 10;
        AICounter++;
        color = new Color(0 + 25*aiId, 0, 255 - 25*aiId);
        newPartsNumber = Random.Range(2, 6);
    }

    private SnakeAI(Board board, int id)
    {
        super(board);
        aiId = id;
        color = new Color(0 + 25*aiId, 0, 255 - 25*aiId);
    }

        /**
     * This method move snake using pathfinding.
     */
    @Override
    protected void control()
    {
        if (thread != null)
        {
            try
            {
                thread.join();
                thread = null;

                var path = pathFinding.Result;
                if (path.size() > 1)
                    direction = path.get(1).clone().getSubtracted(path.get(0).clone());
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }

        Cell closestFruit = board.GetClosestFruit(board.GetCell(Head().position.clone()));
        if (closestFruit == null)
            closestFruit = board.GetRandomEmptyCell();

        pathFinding = new Pathfinding(Vector2D.add(Head().position, direction), closestFruit.position.clone(), board);
        thread = new Thread(pathFinding);
        thread.start();
    }

    
    @Override
    protected void onDestroy()
    {
        game.initialize(new SnakeAI(board, aiId));
    }

    @Override
    protected void render(Graphics2D g)
    {
        Font font = new Font("Arial", Font.BOLD, 18);
        g.setFont(font);
        g.setColor(color);
        g.drawString("AI:" + points, Gameplay.WIDTH_OF_WINDOW - (aiId+1) * 40, Gameplay.HEIGHT_OF_WINDOW - 10);
    }
}
