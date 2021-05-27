import java.awt.Color;
import java.awt.Graphics2D;

enum FruitType {
    Apple,
}

public class Fruit extends GameObject {

    public FruitType type;

    private Cell parentCell;
    private FruitGenerator fruitGenerator;

    private Color color = Color.red; //todo zamienić color na sprite


    public Fruit(Cell parentCell, FruitType type, FruitGenerator fruitGenerator) {
        this.parentCell = parentCell;
        this.type = type;
        this.fruitGenerator = fruitGenerator;
    }

    public int Points() {
        switch (type) {
            case Apple:
                return 10;

            default:
                return 0;
        }
    }

    @Override
    protected void render(Graphics2D g) {
        g.setColor(color);
        g.fillRect(parentCell.position.x * Gameplay.CELL_WIDTH, parentCell.position.y * Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH,
                Gameplay.CELL_HEIGHT);
    }

    @Override
    protected void onDestroy()
    {
        fruitGenerator.DecreaseCounter();
    }
}
