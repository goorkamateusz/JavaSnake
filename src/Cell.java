import java.awt.Color;
import java.awt.Graphics2D;

public class Cell extends GameObject {
    public GameObject content;
    protected Vector2D position = new Vector2D();
    protected Color color;

    public Cell() {
    }

    public Cell(int x, int y) {
        this.position.set(x, y);
        this.color = Color.GRAY;
    }

    public Cell(int x, int y, Color color) {
        this.position.set(x, y);
        this.color = color;
    }

    @Override
    protected void render(Graphics2D g) {
        g.setColor(color);
        g.fillRect(position.x * Gameplay.CELL_WIDTH, position.y * Gameplay.CELL_HEIGHT, Gameplay.CELL_WIDTH, Gameplay.CELL_HEIGHT);
    }
}
