import java.awt.Color;
import java.awt.Graphics2D;

enum FruitType
{
    Apple,
}

/**
 * Static GameObject which grants points to Snake after collision.
 */
public class Fruit extends GameObject
{

    public FruitType type;

    private Cell parentCell;
    private FruitGenerator fruitGenerator;

    private Color color = Color.red; // todo zamienić color na sprite

    /**
     * Costructor that set position and type of object. Pass reference to fruit
     * generator,
     */
    public Fruit(Cell parentCell, FruitType type, FruitGenerator fruitGenerator)
    {
        this.parentCell = parentCell;
        this.type = type;
        this.fruitGenerator = fruitGenerator;
    }


    @Override
    protected void render(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(parentCell.position.x * Gameplay.CELL_WIDTH, parentCell.position.y * Gameplay.CELL_HEIGHT,
                Gameplay.CELL_WIDTH, Gameplay.CELL_HEIGHT);
    }

    @Override
    protected void onDestroy()
    {
        fruitGenerator.DecreaseCounter();
    }
}
