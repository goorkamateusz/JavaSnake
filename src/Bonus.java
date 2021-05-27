import java.awt.Color;

public class Bonus extends Cell {
    public Bonus() {
        color = new Color(Random.Range(0, 255), Random.Range(0, 255), Random.Range(0, 255));
    }

    @Override
    protected void awake() {
    }

    @Override
    protected void start() {
        position.x = Random.Range(0, Gameplay.CELLS_X);
        position.y = Random.Range(0, Gameplay.CELLS_Y);
    }

    @Override
    protected void update() {
    }
}
