import java.awt.Color;
import java.awt.Graphics2D;

public class SnakePart extends GameObject 
{
    //Sprite sprite
    protected int x;
    protected int y;
    protected Color color;
    
    public SnakePart(int x, int y) 
    {
        this.x = x;
        this.y = y;
        this.color = Color.green;
    }

    @Override
    protected void render(Graphics2D g) 
    {
        g.setColor(color);
        g.fillRect(x*Gameplay.CELL_WIDTH, y*Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH, Gameplay.CELL_HEIGHT);
    }
}
