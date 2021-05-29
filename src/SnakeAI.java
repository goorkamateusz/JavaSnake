import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SnakeAI extends Snake
{
    private Thread thread = null;
    private PathFinding pathFinding;

    public SnakeAI(Board board)
    {
        super(board);
        color = Color.blue;
    }

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
                if (path.size() > 2)
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

        pathFinding = new PathFinding(Head().position.clone(), closestFruit.position.clone(), board);
        thread = new Thread(pathFinding);
        thread.start();
    }

    @Override
    protected void onDestroy()
    {
        game.initialize(new SnakeAI(board));
    }

    @Override
    protected void render(Graphics2D g)
    {
        Font font = new Font("Arial", Font.PLAIN, 18);
        g.setFont(font);
        g.setColor(color);
        g.drawString("AI: " + points, Gameplay.WIDTH_OF_WINDOW - 80, Gameplay.HEIGHT_OF_WINDOW - 10);
    }
}
