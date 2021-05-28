import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends GameObject
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color color;

    @Override
    protected void render(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
