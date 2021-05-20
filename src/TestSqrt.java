import java.awt.Color;

public class TestSqrt extends Rectangle {

    private float sign = 1;
    private int max = 600;
    private int min = -50;

    @Override
    protected void awake() {
        width = 40;
        height = 40;
        color = new Color(64, 64, 64);
    }

    @Override
    protected void start() {
        x = (int) Math.floor(Math.random()*(max-min+1)+min);
        y = (int) Math.floor(Math.random()*(max-min+1)+min);
    }

    @Override
    protected void update() {
        x += sign * getDeltaTime() * 0.5;
        if (x > 600)
        {
            sign = -1;
            game.initialize(new TestSqrt());
        }
        if (x < 0)
        {
            game.destroy(this);
            sign = 1;
        }
    }
}