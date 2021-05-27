import java.awt.Color;
import java.awt.Graphics2D;

public class SnakePart extends GameObject 
{
    //Sprite sprite
    public Vector2D position;
    protected Color color;

    public SnakePart(Vector2D position) {
        this.position = position;
        this.color = Color.green;
    }

    @Override
    protected void render(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(position.x*Gameplay.CELL_WIDTH, position.y*Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH, Gameplay.CELL_HEIGHT);
    }
}
