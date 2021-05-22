import java.awt.Color;

public class Bonus extends Cell {
    public Bonus() {
        color = new Color(Random.Range(0, 255), Random.Range(0, 255), Random.Range(0, 255));
    }

    public Bonus(Color color) {
        super(color);
    }

    @Override
    protected void awake() {
    }

    @Override
    protected void start() {
        x = Random.Range(0, Gameplay.CELLS_X);
        y = Random.Range(0, Gameplay.CELLS_Y);
    }

    @Override
    protected void update() {
    }
}
