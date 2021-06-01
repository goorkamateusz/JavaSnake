import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Base to build enviroment. Every cell contains reference to object on it.
 * If cell is empty content == null.
 */
public class Cell extends GameObject
{
    public GameObject content;
    protected Vector2D position = new Vector2D();
    protected Color color;

    //Constructor to set cell position in game screen.
    public Cell(int x, int y)
    {
        this.position.set(x, y);
        this.color = Color.GRAY;
    }

    @Override
    protected void render(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(position.x * Gameplay.CELL_WIDTH, position.y * Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH,
                Gameplay.CELL_HEIGHT);
    }
}
