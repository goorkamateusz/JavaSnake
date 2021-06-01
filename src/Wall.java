import java.awt.Color;

/**
 * Class to implement wall as obstacle.
 */
public class Wall extends Cell
{
    /**
     * Set wall positions.
     */
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    protected void awake()
    {
        color = Color.BLACK;
    }
}
