import java.awt.Color;
import java.awt.Graphics2D;

public class Cell extends GameObject {
    protected int x;
    protected int y;
    protected Color color;

    public Cell() {}

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(Color color) {
        this.color = color;
    }

    public Cell(int x, int y, Color color) {
        this(x, y);
        this.color = color;
    }

    @Override
    protected void render(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x*Gameplay.CELL_WIDTH, y*Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH, Gameplay.CELL_HEIGHT);
    }
}
