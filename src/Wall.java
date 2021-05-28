import java.awt.Color;

public class Wall extends Cell
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    protected void awake()
    {
        color = Color.BLACK;
    }

    @Override
    protected void start()
    {

    }

    @Override
    protected void update()
    {

    }
}
