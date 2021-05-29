import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SnakeAI extends Snake
{
    public SnakeAI(Board board)
    {
        super(board);
        color = Color.blue;
    }


    @Override
    protected void Control()
    {
        Cell closestFruit = board.GetClosestFruit(board.GetCell(Head().position.clone()));
        if (closestFruit == null)
            closestFruit = board.GetRandomEmptyCell();
        Pathfinding pathfinding = new Pathfinding();
        var path = pathfinding.A_Star(Head().position.clone(), closestFruit.position.clone(), board);
        direction = path.get(1).clone().getSubtracted(path.get(0).clone());
    }

    @Override
    protected void render(Graphics2D g)
    {
        Font font = new Font("Arial", Font.PLAIN, 18);
        g.setFont(font);
        g.setColor(color);
        g.drawString("AI: " + body.size(), Gameplay.WIDTH_OF_WINDOW-80, Gameplay.HEIGHT_OF_WINDOW-10);
    }
}